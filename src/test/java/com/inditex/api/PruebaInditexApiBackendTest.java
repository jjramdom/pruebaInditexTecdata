package com.inditex.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.inditex.service.PriceService;
import com.inditex.utils.Constants;
 
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest( properties = { "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect" })
@AutoConfigureTestDatabase
@TestPropertySource(locations = "classpath:config/application-test.yml")
public class PruebaInditexApiBackendTest {

	@Autowired
	PruebaInditexApiBackend pruebaInditexApiBackend;

	@MockBean
	PriceService priceService;
	
	String productId="55308";
	String brandId="3";
	String time="2020-06-14-10.00.00";

	

	@Test
	public void getPriceByDate()  {
		
		
		Mockito.when(priceService.getPriceByDate(productId, brandId, time)).thenReturn("response");
		assertEquals(new ResponseEntity<>("response", HttpStatus.OK), 
				pruebaInditexApiBackend.getPriceByDate(productId, brandId, time));
		
    }
	
	@Test
	public void getPriceByDate_DateTimeException()  {

		Mockito.when(priceService.getPriceByDate(productId, brandId, time)).thenThrow(DateTimeParseException.class);
		assertEquals(new ResponseEntity<>(Constants.MSG_DATE_FORMAT+Constants.DATE_TIME_FORMAT, HttpStatus.BAD_REQUEST), 
				pruebaInditexApiBackend.getPriceByDate(productId, brandId, time));
    }
	
	@Test
	public void getPriceByDate_NumberFormatException()  {

		Mockito.when(priceService.getPriceByDate(productId, brandId, time)).thenThrow(NumberFormatException.class);
		assertEquals(new ResponseEntity<>(Constants.MSG_ID_FORMAT, HttpStatus.BAD_REQUEST), 
				pruebaInditexApiBackend.getPriceByDate(productId, brandId, time));
	
    }
	
	@Test
	public void getPriceByDate_NullPointerException()  {
		
		Mockito.when(priceService.getPriceByDate(productId, brandId, time)).thenThrow(NullPointerException.class);
		assertEquals(new ResponseEntity<>(Constants.MSG_NO_CONTENT, HttpStatus.NO_CONTENT), 
				pruebaInditexApiBackend.getPriceByDate(productId, brandId, time));
    }
	
	@Test
	public void getAllPrices() {
		
		Mockito.when(priceService.getAllPrices()).thenReturn("response");
		assertEquals(new ResponseEntity<>("response", HttpStatus.OK), 
				pruebaInditexApiBackend.getAllPrices());
	}
	
	@Test
	public void getAllPrices_Empty() {
		
		Mockito.when(priceService.getAllPrices()).thenReturn("");
		assertEquals(new ResponseEntity<>(Constants.MSG_NO_CONTENT, HttpStatus.NO_CONTENT), 
				pruebaInditexApiBackend.getAllPrices());
	}
	
	@Test
	public void getPricesByProduct() {
		
		Mockito.when(priceService.getPricesByProductId(productId)).thenReturn("response");
		assertEquals(new ResponseEntity<>("response", HttpStatus.OK), 
				pruebaInditexApiBackend.getPricesByProduct(productId));

	}
	
	@Test
	public void getPricesByProduct_Empty() {
		
		Mockito.when(priceService.getPricesByProductId(productId)).thenReturn("");
		assertEquals(new ResponseEntity<>(Constants.MSG_NO_CONTENT, HttpStatus.NO_CONTENT), 
				pruebaInditexApiBackend.getPricesByProduct(productId));

	}
	
	@Test
	public void getPricesByProduct_Exception() {
		
		Mockito.when(priceService.getPricesByProductId(productId)).thenThrow(NumberFormatException.class);
		assertEquals(new ResponseEntity<>(Constants.MSG_ID_FORMAT, HttpStatus.BAD_REQUEST), 
				pruebaInditexApiBackend.getPricesByProduct(productId));

	}
	
	@Test
	public void getPricesByProductAndBrandId() {
		Mockito.when(priceService.getPricesByProductIdAndBrandId(productId, brandId)).thenReturn("response");
		assertEquals(new ResponseEntity<>("response", HttpStatus.OK), 
				pruebaInditexApiBackend.getPricesByProductAndBrandId(productId, brandId));
	}
	
	@Test
	public void getPricesByProductAndBrandId_Empty() {
		Mockito.when(priceService.getPricesByProductIdAndBrandId(productId, brandId)).thenReturn("");
		assertEquals(new ResponseEntity<>(Constants.MSG_NO_CONTENT, HttpStatus.NO_CONTENT), 
				pruebaInditexApiBackend.getPricesByProductAndBrandId(productId, brandId));
	}
	
	@Test
	public void getPricesByProductAndBrandId_Exception() {
		Mockito.when(priceService.getPricesByProductIdAndBrandId(productId, brandId)).thenThrow(NumberFormatException.class);
		assertEquals(new ResponseEntity<>(Constants.MSG_ID_FORMAT, HttpStatus.BAD_REQUEST), 
				pruebaInditexApiBackend.getPricesByProductAndBrandId(productId, brandId));
	}
}
