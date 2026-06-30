package com.desafio.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "accounts")
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//TODO Put the scale and RoundinMode. In DDD implementation create a Money class.
	@Column(nullable = false)
	private BigDecimal balance;
	
	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Transaction> transactions = new ArrayList<>();

	//TODO Extract this to a domain model in the DDD implementation
	public void debit(BigDecimal amount) {
		if (hasInsufficientBalance(amount)) {
			throw new RuntimeException("Saldo insuficiente");
		}
		
		balance = balance.subtract(amount);
	}
	
	//TODO Extract this to a domain model in the DDD implementation
	public void credit(BigDecimal amount) {
		balance = balance.add(amount);
	}
	
	//TODO Extract this to a domain model in the DDD implementation
	private boolean hasInsufficientBalance(BigDecimal amount) {
		return amount != null && balance.compareTo(amount) < 0;
	}
	
	public Long getId() {
		return id;
	}

}
