package com.inditex.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inditex.dao.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product,Long>{

}
