package com.inditex;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
 
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PruebaInditexApplicationTest {
 
	@Autowired
	PruebaInditexApplication pruebaInditexApplication;
 
  
	@Test
	void main() {
		pruebaInditexApplication.main(new String[] { "Hello", "World" });
		assertTrue(true);
	}
}
