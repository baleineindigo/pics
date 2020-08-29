package com.devils.pics.dao;

import com.devils.pics.domain.Customer;

public interface CustomerDAO {
	int registerCustomer(Customer customer) throws Exception;
	Customer getCustomer(String custId) throws Exception;
	int updateCustomer(Customer customer) throws Exception;
	int deleteCustomer(int custId) throws Exception;
}

