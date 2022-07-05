package com.inditex.api;


import java.time.format.DateTimeParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inditex.service.PriceService;
import com.inditex.utils.Constants;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RequiredArgsConstructor

/** The Constant log. */
@Slf4j
@RestController
@RequestMapping("/api/precios")
public class PruebaInditexApiBackend {

   @Autowired
   private PriceService priceService;

    @GetMapping("/{productId}/{brandId}/{time}")
    public ResponseEntity<String> getPriceByDate(@PathVariable("productId") String productId, 
    		@PathVariable("brandId") String brandId, @PathVariable("time") String time) {
        log.info("INI getPriceByDate");
        try {
        	String response = priceService.getPriceByDate(productId,brandId,time);
            log.info("FIN getPriceByDate");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch(DateTimeParseException e) {
        	log.error("Error en getPriceByDate: " + e);
        	return new ResponseEntity<>(Constants.MSG_DATE_FORMAT+Constants.DATE_TIME_FORMAT, HttpStatus.BAD_REQUEST);
        }catch(NumberFormatException e) {
        	log.error("Error en getPriceByDate: " + e);
        	return new ResponseEntity<>(Constants.MSG_ID_FORMAT, HttpStatus.BAD_REQUEST);
        }catch(NullPointerException e) {
        	log.error("Error en getPriceByDate: " + e);
        	return new ResponseEntity<>(Constants.MSG_NO_CONTENT, HttpStatus.NO_CONTENT);
        }catch (Exception e) {
        	log.error("Error en getPriceByDate: " + e);
        	return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping
    public ResponseEntity<String> getAllPrices() {
        log.info("INI getAllPrices");
        try {
            String response = priceService.getAllPrices();
            log.info("FIN getAllPrices");
            if(response.isEmpty())
            	return new ResponseEntity<>(Constants.MSG_NO_CONTENT, HttpStatus.NO_CONTENT);
            else
            	return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
        	log.error("Error en getAllPrices: " + e);
        	return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{productId}")
    public ResponseEntity<String> getPricesByProduct(@PathVariable("productId") String productId) {
        log.info("INI getPricesByProduct");
        try {
            String response = priceService.getPricesByProductId(productId);
            log.info("FIN getPricesByProduct");
            if(response.isEmpty())
            	return new ResponseEntity<>(Constants.MSG_NO_CONTENT, HttpStatus.NO_CONTENT);
            else
            	return new ResponseEntity<>(response, HttpStatus.OK);
        } catch(NumberFormatException e) {
        	log.error("Error en getPricesByProduct: " + e);
        	return new ResponseEntity<>(Constants.MSG_ID_FORMAT, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
        	log.error("Error en getPricesByProduct: " + e);
        	return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{productId}/{BrandId}")
    public ResponseEntity<String> getPricesByProductAndBrandId(@PathVariable("productId") String productId, 
    		@PathVariable("brandId") String brandId) {
        log.info("INI getPricesByProductAndBrandId");
        try {
            String response = priceService.getPricesByProductIdAndBrandId(productId, brandId);
            log.info("FIN getPricesByProductAndBrandId");
            if(response.isEmpty())
            	return new ResponseEntity<>(Constants.MSG_NO_CONTENT, HttpStatus.NO_CONTENT);
            else
            	return new ResponseEntity<>(response, HttpStatus.OK);
        }catch(NumberFormatException e) {
        	log.error("Error en getPricesByProductAndBrandId: " + e);
        	return new ResponseEntity<>(Constants.MSG_ID_FORMAT, HttpStatus.BAD_REQUEST);
        }catch (Exception e) {
        	log.error("Error en getPricesByProductAndBrandId: " + e);
        	return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
