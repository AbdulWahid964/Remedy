package com.remedy.dao;

import org.springframework.data.repository.CrudRepository;

import com.remedy.entity.Users;

/**
 * @author Abdul
 *
 */
public interface UsersDao extends CrudRepository<Users, Integer> {

	public Users findByCustomerName(String customerName);
}
