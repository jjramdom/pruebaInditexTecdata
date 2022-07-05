package com.inditex.service;

import java.time.format.DateTimeParseException;

public interface PriceService {
	
	public String getPriceByDate(String idProduct,String idBrand, String time) throws DateTimeParseException, NullPointerException, NumberFormatException;
	
	public String getAllPrices();
	
	public String getPricesByProductId(String productId)throws NumberFormatException;
	
	public String getPricesByProductIdAndBrandId(String productId, String brandId)throws NumberFormatException;
}
