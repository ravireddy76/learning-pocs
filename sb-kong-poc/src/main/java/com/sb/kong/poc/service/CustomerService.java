package com.sb.kong.poc.service;

import com.sb.kong.poc.model.Customer;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    private static final String KEY_PREFIX = "cust";
    private Map<String, Customer> customerData = new HashMap<>();

    public Customer createCustomer(Customer customer) {
        String key = KEY_PREFIX + customer.getId();
        customerData.put(key, customer);
        return customer;
    }

    public Customer updateCustomer(Customer customer) {
        String existingCustomerId = KEY_PREFIX + customer.getId();
        customerData.put(existingCustomerId, customer);
        return customer;
    }

    public String deleteCustomer(int customerId) {
        String customerKey = KEY_PREFIX + customerId;
        Customer customer = customerData.get(customerKey);
        boolean isDeleteSuccess = customerData.remove(customerKey, customer);

        String message = isDeleteSuccess ? "Customer deleted successfully" : "There is not customer available to delete";
        return message;
    }

    public Customer findCustomerById(int customerId) {
        String searchCustomerKey = KEY_PREFIX + customerId;
        Customer searchedCustomer = customerData.get(searchCustomerKey);
        return searchedCustomer;
    }

    public List<Customer> findAlCustomers() {
        List<Customer> customers = customerData.values().stream()
                .collect(Collectors.toList());
        return customers;
    }
}
