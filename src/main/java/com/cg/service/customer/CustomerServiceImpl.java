package com.cg.service.customer;


import com.cg.model.Customer;

import com.cg.model.LocationRegion;

import com.cg.model.dto.CustomerResDTO;

import com.cg.repository.ICustomerRepository;

import com.cg.repository.ILocationRegionRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private ICustomerRepository customerRepository;

    @Autowired
    private ILocationRegionRepository locationRegionRepository;

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public List<CustomerResDTO> findAllCustomerResDTO(Boolean deleted) {
        return customerRepository.findAllCustomerResDTO(deleted);
    }

    @Override
    public Customer create(Customer customer) {
        LocationRegion locationRegion = customer.getLocationRegion();
        locationRegionRepository.save(locationRegion);

        customer.setLocationRegion(locationRegion);
        customerRepository.save(customer);

        return customer;
    }

    @Override
    public Customer update(Customer customer) {

        Customer oldCus = findById(customer.getId()).get();
        customer.setDeleted(oldCus.getDeleted());
        locationRegionRepository.save(customer.getLocationRegion());
        customerRepository.save(customer);

        return customer;
    }
    @Override
    public Customer save(Customer customer) {
        return null;
    }

    @Override
    public void delete(Customer customer) {

    }
    @Override
    public void deleteById(Long aLong) {

    }
}
