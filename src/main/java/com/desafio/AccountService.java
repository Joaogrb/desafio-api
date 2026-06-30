package com.desafio;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AccountService {
	
	private final AccountRepository accountRepository;
	
	private final TransactionRepository transactionRepository;
	
	public AccountService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

	public List<AccountResponse> getAll() {
		return accountRepository.findAllProjectedBy();
	}
	
	public AccountResponse getById(Long accountId) {
		return accountRepository.findProjectedById(accountId)
				.orElseThrow(() -> new ResourceNotFoundException(accountId));
	}
	
	//TODO realize this processing asynchronous, change to send a message, return a 202 Accepted
	@Transactional(rollbackFor = Exception.class)
	public TransactionCreateReponse create(Long accountId, TransactionRequest req) {
		if (accountId.equals(req.relatedAccountId())) {
			throw new IllegalArgumentException(
					"It is not possible to perform a transaction between accounts with the same ID");
		}
		
		Account account = accountRepository.findByIdWithLock(accountId)
				.orElseThrow(() -> new ResourceNotFoundException(accountId));
		Account relatedAccount = accountRepository.findByIdWithLock(req.relatedAccountId())
				.orElseThrow(() -> new ResourceNotFoundException(req.relatedAccountId()));

		account.debit(req.amount());
		relatedAccount.credit(req.amount());

		// only for code readability
		accountRepository.save(account);
		accountRepository.save(relatedAccount);
		
		UUID transaction_group_id = UUID.randomUUID();
		Transaction debitTransaction = new Transaction(account, req.amount().negate(), transaction_group_id);
		Transaction creditTransaction = new Transaction(relatedAccount, req.amount(), transaction_group_id);

		Transaction savedDebitTransaction = transactionRepository.save(debitTransaction);
		transactionRepository.save(creditTransaction);

		return new TransactionCreateReponse(
				savedDebitTransaction.getTransactionGroupId(),
				savedDebitTransaction.getAmount(),
				savedDebitTransaction.getCreatedDate());
	}
	
}
