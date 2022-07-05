package com.inditex.repositories;

import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inditex.dao.Price;
import com.inditex.dao.PricePK;

@Repository
public interface PriceRepository extends JpaRepository<Price,PricePK>{
	
	public Price getPriceByDate(Map<String, Object> conditions) throws DateTimeParseException, NullPointerException, NumberFormatException;
	
	public List<Price> findAll();
	
	public List<Price> findAllByProductId(String value) throws NumberFormatException;
	
	public List<Price> findAllByProductIdAndBrand(String productId, String brandId)throws NumberFormatException;
}
