package com.desafio.entity;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "transactions")
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private UUID transactionGroupId;
	
	@NotNull
	@Column(nullable = false)
	private BigDecimal amount;
	
	@CreationTimestamp @Column(nullable = false)
	OffsetDateTime createdDate;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "account_id", nullable = false)
	private Account account;

	protected Transaction() {}
	
	public Transaction(Account account, BigDecimal amount, UUID transactionGroupId) {
		this.account = account;
		this.amount = amount;
		this.transactionGroupId = transactionGroupId;
	}
	
	public UUID getTransactionGroupId() {
		return transactionGroupId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public OffsetDateTime getCreatedDate() {
		return createdDate;
	}

}
