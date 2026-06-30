package com.desafio.dto;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "balance", "transactions" })
public interface AccountResponse {
	Long getId();
	BigDecimal getBalance();
	List<TransactionResponse> getTransactions();
}
