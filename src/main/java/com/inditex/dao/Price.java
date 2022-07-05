package com.inditex.dao;


import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "prices")
public class Price {
	
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId 
	private PricePK pricePK;

	@Column(name="START_DATE")
	private LocalDateTime startDate;
	@Column(name="END_DATE")
	private LocalDateTime endDate;
	@Column(name="PRIORITY")
	private int priority;
	@Column(name="PRICE")
	private BigDecimal price;
	@Column(name="CURR")
	private String curr;
	
}