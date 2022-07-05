package com.inditex.service.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mockStatic;

import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.inditex.dao.Price;
import com.inditex.repositories.impl.PriceRepositoryImpl;
import com.inditex.utils.Constants;
import com.inditex.utils.Mapper;
 
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest( properties = { "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect" })
@AutoConfigureTestDatabase
@TestPropertySource(locations = "classpath:config/application-test.yml")
public class PriceServiceImplTest {

	@Autowired
	PriceServiceImpl priceServiceImpl;

	
	@MockBean
	PriceRepositoryImpl priceRepositoryImpl;
	
	@Mock
	Price price;

	String productId="55308";
	String brandId="3";
	String time="2020-06-14-10.00.00";
	
	@Test
    public void getPriceByDate() {
	
		HashMap<String,Object> hm= new HashMap<>();
		hm.put(Constants.PRODUCT_ID,productId);
		hm.put(Constants.BRAND_ID,brandId);
		hm.put(Constants.TIME,time);
		
		Mockito.when(priceRepositoryImpl.getPriceByDate(hm)).thenReturn(price);
		MockedStatic<Mapper> mocked = mockStatic(Mapper.class);
		mocked.when(()->Mapper.convertToJson(price)).thenReturn("precio");
		assertNotNull(priceServiceImpl.getPriceByDate(productId,brandId,time));
	}
}
