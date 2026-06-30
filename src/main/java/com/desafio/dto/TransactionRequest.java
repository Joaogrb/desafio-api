package com.desafio.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record TransactionRequest(
		@NotNull Long relatedAccountId,
		@NotNull @Positive BigDecimal amount){}