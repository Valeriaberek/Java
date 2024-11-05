package com.keyce.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.keyce.demo.model.UserModel;

public interface UserRepository extends CrudRepository<UserModel,Long> {

}
