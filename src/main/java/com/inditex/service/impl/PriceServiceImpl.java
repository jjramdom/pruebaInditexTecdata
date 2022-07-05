package com.inditex.service.impl;

import java.time.format.DateTimeParseException;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inditex.dao.Price;
import com.inditex.repositories.PriceRepository;
import com.inditex.service.PriceService;
import com.inditex.utils.Constants;
import com.inditex.utils.Mapper;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class PriceServiceImpl implements PriceService{
	
	
	@Autowired
	PriceRepository priceRepository;

	@Override
	public String getPriceByDate(String idProduct,String idBrand, String time) throws DateTimeParseException, NullPointerException, NumberFormatException{
		
		HashMap<String,Object> hm= new HashMap<>();
		hm.put(Constants.PRODUCT_ID,idProduct);
		hm.put(Constants.BRAND_ID,idBrand);
		hm.put(Constants.TIME,time);
		Price precio=priceRepository.getPriceByDate(hm);
		
	    return Mapper.convertToJson(precio);
		
	}

	@Override
	public String getAllPrices() {
		
		return Mapper.convertToJson(priceRepository.findAll());
		
	}

	@Override
	public String getPricesByProductId(String productId) throws NumberFormatException{
		
		return Mapper.convertToJson(priceRepository.findAllByProductId(productId));
		
	}

	@Override
	public String getPricesByProductIdAndBrandId(String productId, String brandId) throws NumberFormatException{
		
		return Mapper.convertToJson(priceRepository.findAllByProductIdAndBrand(productId, brandId));
		
	}

	
}
