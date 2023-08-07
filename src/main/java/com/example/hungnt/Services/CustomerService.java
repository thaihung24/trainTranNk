package com.example.hungnt.Services;

import com.example.hungnt.models.CustomersEntity;
import com.example.hungnt.repositories.CustomerRepository;
import com.example.hungnt.utils.RequestLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    public List<CustomersEntity> getListCustomer(){
        try{
            return (List<CustomersEntity>) customerRepository.findAll();
        }catch (Exception e){
            return null;
        }

    }
    public CustomersEntity getCustomerByEmail(String email){
        return customerRepository.findCustomersEntitiesByEmail(email);
    }
    public CustomersEntity registerCustomer(CustomersEntity customer){
        customer.hasPassword(customer.getPassword());
        return customerRepository.save(customer);
    }
    public CustomersEntity login(RequestLogin req){
        CustomersEntity cus = customerRepository.findCustomersEntitiesByEmail(req.getEmail());
        if(cus.comparePassword(req.getPassword())){
            return cus;
        }
        else{
            return null;
        }
    }
    public Optional<CustomersEntity> findCustomerById(long id){
        return customerRepository.findById(id);
    }
    public boolean delete(long id){
        try{
            if(customerRepository.findById(id).isPresent()){
                try {
                    customerRepository.deleteById(id);
                    return true;
                }catch (Exception e){
                    return false;
                }
            }else{
                return false;
            }
        }catch (Exception e){
            return false;
        }
    }
}
