package com.remedy.service;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.remedy.dao.UsersDao;
import com.remedy.entity.Users;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

	@Autowired
	UsersDao usersDao;

	@Override
	public void saveUser(Users users) {
		logger.info("In Save Service Method ");
		usersDao.save(users);
	}
}
