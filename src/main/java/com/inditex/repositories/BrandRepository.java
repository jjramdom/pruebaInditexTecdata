package com.inditex.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inditex.dao.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand,Long>{

}
