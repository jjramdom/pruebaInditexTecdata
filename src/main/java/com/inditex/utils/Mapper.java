package com.inditex.utils;

import java.util.List;

import org.json.simple.JSONObject;

import com.inditex.dao.Price;

public class Mapper {
	
	private Mapper() {
	    throw new IllegalStateException("Mapper class");
	 }
	/**
	 * @param precio
	 * @return
	 */
	public static String convertToJson(Price precio) {
		
		JSONObject json = new JSONObject();
		
	    json.put(Constants.PRODUCT_ID, precio.getPricePK().getProduct().getId());
	    json.put(Constants.BRAND_ID, precio.getPricePK().getBrand().getId());
	    json.put(Constants.TARIFA, precio.getPricePK().getPriceList());
	    json.put(Constants.START_DATE, precio.getStartDate());
	    json.put(Constants.END_DATE, precio.getEndDate());
	    json.put(Constants.PRECIO, precio.getPrice());
	    
		return json.toJSONString();
		
	}
	
	public static String convertToJson(List<Price> precios) {
		
		StringBuilder prices = new StringBuilder();
		
		precios.forEach((final Price price) -> prices.append(Mapper.convertToJson(price)));
		
		return prices.toString();
	}
}
