package com.desafio;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public record TransactionCreateReponse(
		UUID transactionGroupId,
		BigDecimal amount,
		OffsetDateTime createdDate) {}