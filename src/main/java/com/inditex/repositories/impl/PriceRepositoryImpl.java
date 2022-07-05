package com.inditex.repositories.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import com.inditex.dao.Price;
import com.inditex.dao.PricePK;
import com.inditex.repositories.PriceRepository;
import com.inditex.utils.Constants;
import com.inditex.utils.Utils;

public class PriceRepositoryImpl implements PriceRepository{
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override	
	public Price getPriceByDate(Map<String, Object> conditions) throws DateTimeParseException, NullPointerException, NumberFormatException
	{
		
		CriteriaBuilder cbPrice = entityManager.getCriteriaBuilder();
		CriteriaQuery<Price> queryPrice = cbPrice.createQuery(Price.class);
		Root<Price> rootPrice = queryPrice.from(Price.class);

		List<Predicate> predicates = new ArrayList<>();
		conditions.forEach((field, value) -> {
			switch (field) {
			case Constants.PRODUCT_ID:
				predicates.add(cbPrice.equal(rootPrice.get(Constants.PRICE_PK).get(Constants.PRODUCT).get(Constants.ID), Long.parseLong(value.toString())));
				break;
			case Constants.BRAND_ID:
				predicates.add(cbPrice.equal(rootPrice.get(Constants.PRICE_PK).get(Constants.BRAND).get(Constants.ID), Long.parseLong(value.toString())));
				break;
			case Constants.TIME:
				predicates.add(cbPrice.lessThanOrEqualTo(rootPrice.<LocalDateTime>get(Constants.START_DATE), Utils.formatDateTime(value)));
				predicates.add(cbPrice.greaterThanOrEqualTo(rootPrice.<LocalDateTime>get(Constants.END_DATE),Utils.formatDateTime(value)));
				break;
			}
		});
		queryPrice.orderBy(cbPrice.asc(rootPrice.get(Constants.PRIORITY)));
		queryPrice.select(rootPrice).where(predicates.toArray(new Predicate[predicates.size()]));
		//return entityManager.createQuery(queryPrice).getResultList().get(0);
		Optional<Price> optionalPrice = entityManager.createQuery(queryPrice).getResultList().stream().findFirst();
			    
		return optionalPrice.isPresent() ? optionalPrice.get() : null;
		
				
		}

	
	@Override
	public List<Price> findAll() {
		CriteriaBuilder cbPrice = entityManager.getCriteriaBuilder();
		CriteriaQuery<Price> queryPrice = cbPrice.createQuery(Price.class);
		Root<Price> rootPrice = queryPrice.from(Price.class);
		queryPrice.select(rootPrice);
		return entityManager.createQuery(queryPrice).getResultList();
	}
	@Override
	public List<Price> findAllByProductId(String value) throws NumberFormatException{
		CriteriaBuilder cbPrice = entityManager.getCriteriaBuilder();
		CriteriaQuery<Price> queryPrice = cbPrice.createQuery(Price.class);
		Root<Price> rootPrice = queryPrice.from(Price.class);
		queryPrice.select(rootPrice).where(cbPrice.equal(rootPrice.get(Constants.PRICE_PK).get(Constants.PRODUCT).get(Constants.ID), Long.parseLong(value)));
		return entityManager.createQuery(queryPrice).getResultList();
	}
	@Override
	public List<Price> findAllByProductIdAndBrand(String productId, String brandId) throws NumberFormatException{
		CriteriaBuilder cbPrice = entityManager.getCriteriaBuilder();
		CriteriaQuery<Price> queryPrice = cbPrice.createQuery(Price.class);
		Root<Price> rootPrice = queryPrice.from(Price.class);
		List<Predicate> predicates = new ArrayList<>();
		predicates.add(cbPrice.equal(rootPrice.get(Constants.PRICE_PK).get(Constants.PRODUCT).get(Constants.ID), Long.parseLong(productId)));
		predicates.add(cbPrice.equal(rootPrice.get(Constants.PRICE_PK).get(Constants.BRAND).get(Constants.ID), Long.parseLong(brandId)));
		return entityManager.createQuery(queryPrice).getResultList();
	}


	@Override
	public List<Price> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Price> findAllById(Iterable<PricePK> ids) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public <S extends Price> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public <S extends Price> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public <S extends Price> List<S> saveAllAndFlush(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void deleteAllInBatch(Iterable<Price> entities) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void deleteAllByIdInBatch(Iterable<PricePK> ids) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Price getOne(PricePK id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Price getById(PricePK id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Price getReferenceById(PricePK id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public <S extends Price> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public <S extends Price> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Page<Price> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public <S extends Price> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Optional<Price> findById(PricePK id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean existsById(PricePK id) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public void deleteById(PricePK id) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void delete(Price entity) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void deleteAllById(Iterable<? extends PricePK> ids) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void deleteAll(Iterable<? extends Price> entities) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public <S extends Price> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public <S extends Price> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public <S extends Price> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public <S extends Price> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public <S extends Price, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		// TODO Auto-generated method stub
		return null;
	}
}
