package com.desafio;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "transactionGroupId", "amount", "createdDate" })
interface TransactionResponse {
	UUID getTransactionGroupId();
	BigDecimal getAmount();
	OffsetDateTime getCreatedDate();
}
