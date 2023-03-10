package com.cantvas2.cantvas2.repository;

import org.springframework.data.repository.CrudRepository;

import com.cantvas2.cantvas2.models.CantvasUser;

public interface UserRepository extends CrudRepository<CantvasUser, Long>{
    
}
