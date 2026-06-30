package com.desafio;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class AccountController {
	
	private final AccountService service;
	
	public AccountController(AccountService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<List<AccountResponse>> getAll() {
		return ResponseEntity.ok(service.getAll());
	}
	
	@GetMapping("/{accountId}")
	public ResponseEntity<AccountResponse> getById(@PathVariable Long accountId) {
		return ResponseEntity.ok(service.getById(accountId));
	}

	@PostMapping("/{accountId}/transactions")
	public ResponseEntity<TransactionCreateReponse> create(@PathVariable Long accountId,
			@Valid @RequestBody TransactionRequest req) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.create(accountId, req));
	}
}
