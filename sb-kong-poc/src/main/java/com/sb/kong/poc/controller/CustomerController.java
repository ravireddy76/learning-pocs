package com.sb.kong.poc.controller;

import com.sb.kong.poc.model.Customer;
import com.sb.kong.poc.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping(value = "/sbk-service/v1/customer", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "/sbk-service/v1/customer", description = "Customer Service")
@Slf4j
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    /**
     * Method to create customer.
     *
     * @param customer
     * @return ResponseEntity
     */
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "", notes = "Creating new customer")
    @RequestMapping(value = "/customer", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> create(@RequestBody Customer customer) {
        try {
            log.info("Creating  new customer: {}", customer.toString());
            Customer registeredCustomer = customerService.createCustomer(customer);
            return new ResponseEntity<>(registeredCustomer, HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Issues to create or register customer: {}", ExceptionUtils.getStackTrace(ex));
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Method to update customer.
     *
     * @param customer
     * @return ResponseEntity
     */
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "", notes = "Updating existing customer")
    @RequestMapping(value = "/customer", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> update(@RequestBody Customer customer) {
        try {
            log.info("Updating existing customer: {}", customer.toString());
            Customer updatedCustomer = customerService.updateCustomer(customer);
            return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Issues to updating existing customer: {}", ExceptionUtils.getStackTrace(ex));
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Method to delete customer.
     *
     * @param customerId
     * @return ResponseEntity
     */
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "", notes = "Updating existing customer")
    @RequestMapping(value = "/customer/{customerId}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> delete(
            @ApiParam(value = "customerId", required = true) @PathVariable("customerId") int customerId) {
        try {
            log.info("Deleting existing customer by id: {}", customerId);
            customerService.deleteCustomer(customerId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Issues to delete customer by customerId: {}, with exception: {}", customerId, ExceptionUtils.getStackTrace(ex));
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Method to get customer by customerId.
     *
     * @param customerId
     * @return ResponseEntity
     */
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "", notes = "Get customer by customer id")
    @RequestMapping(value = "/customer/{customerId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> getCustomerById(@PathVariable int customerId) {
        try {
            log.info("Search customer by customerId : " + customerId);
            Customer searchedCustomer = customerService.findCustomerById(customerId);
            return new ResponseEntity<>(searchedCustomer, HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Issues to get customer by customerId: {}, with exception: {}", customerId, ExceptionUtils.getStackTrace(ex));
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }

    /**
     * Method to get all customers.
     *
     * @return ResponseEntity
     */
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "", notes = "Get all customers")
    @RequestMapping(value = "/customer/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Customer>> getAllCustomers() {
        try {
            log.info("Search all customers");
            List<Customer> customers = customerService.findAlCustomers();
            return new ResponseEntity<>(customers, HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Issues to get all customers exception: {}", ExceptionUtils.getStackTrace(ex));
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
