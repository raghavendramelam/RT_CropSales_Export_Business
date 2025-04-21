package com.raghava.rt.service;

import com.raghava.rt.bindings.*;
import com.raghava.rt.entity.Customer;
import com.raghava.rt.repository.CustomerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class IServiceCustImpl implements  ICustService{

    private final CustomerRepository customerRepository;
    public IServiceCustImpl(CustomerRepository customerRepository){
        this.customerRepository=customerRepository;
    }
    @Override
    public boolean registerCustomer ( CustomerBinding customerBinding ) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerBinding,customer);
      return  customerRepository.save(customer).getId()!=null;

    }

    @Override
    public ResultBinding calcuatedAmount ( AmountBinding amountBinding ) {
        Customer customer;
   Optional<Customer> cust = customerRepository.findByMobile(amountBinding.getMobile());

   if(cust.isPresent()){
    customer=   cust.get();
   }else{
       throw new RuntimeException("Customer not found with given phone Number");
   }

     Double d =   calculateBilling(amountBinding,customer );
   if(amountBinding.getCrop_in_storage()){
       LocalDate storageDate = customer.getStorage_date();
       LocalDate present = LocalDate.now();
       long days_kept=  ChronoUnit.DAYS.between(storageDate, present);
       Double storageCost=  (days_kept*customer.getStorage_amount_per_day());
       customer.setCalculated_amount(d-storageCost);
       customer.setWorkers_cost(amountBinding.getWorkers_cost());
       customer.setTravelling_cost(amountBinding.getTravelling_cost());
       customer.setTotal_in_kgs(amountBinding.getTotal_in_kgs());
       customer.setRate_sold_to(amountBinding.getRate_sold_to());
       customer.setCrop_sold_date(amountBinding.getCrop_sold_date());
       customer.setCommission(amountBinding.getCommission());
       customerRepository.save(customer);
   }else {
       customer.setCalculated_amount(d);
       customer.setWorkers_cost(amountBinding.getWorkers_cost());
       customer.setTravelling_cost(amountBinding.getTravelling_cost());
       customer.setTotal_in_kgs(amountBinding.getTotal_in_kgs());
       customer.setRate_sold_to(amountBinding.getRate_sold_to());
       customer.setCrop_sold_date(amountBinding.getCrop_sold_date());
       customer.setCommission(amountBinding.getCommission());
       customerRepository.save(customer);
   }
   ResultBinding resultBinding = new ResultBinding();
   BeanUtils.copyProperties(customer,resultBinding);

                return resultBinding;

    }

    @Override
    public String updatedetails ( UpdatCustomerBinding updatCustomerBinding ) {
        Customer customer
                = new Customer();
        BeanUtils.copyProperties(updatCustomerBinding,customer);
        customerRepository.save(customer);
        return "";
    }

    public List<Customer> searchCustomers( CustomerSearchBinding binding) {
        Customer probe = new Customer();

        if (binding.getName() != null && !binding.getName().isBlank()) {
            probe.setName(binding.getName());
        }
        if (binding.getMobile() != null) {
            probe.setMobile(binding.getMobile());
        }
        if (binding.getLocation() != null && !binding.getLocation().isBlank()) {
            probe.setLocation(binding.getLocation());
        }

        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withIgnoreNullValues()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase();

        Example<Customer> example = Example.of(probe, matcher);

        Sort sort = Sort.by(Sort.Direction.ASC,"name","location");
        return customerRepository.findAll(example,sort);
    }

    private Double calculateBilling(AmountBinding amountBinding, Customer customer) {
        if (amountBinding == null || customer == null) {
            throw new IllegalArgumentException("AmountBinding and Customer cannot be null");
        }

        // Step 1: Extract values with null safety
        Double weight = amountBinding.getTotal_in_kgs() != null ? amountBinding.getTotal_in_kgs() : 0.0;
        Double rate = amountBinding.getRate_sold_to() != null ? amountBinding.getRate_sold_to() : 0.0;
        Double amountTakenUpfront = amountBinding.getAmount_taken_upfront() != null ? amountBinding.getAmount_taken_upfront() : 0.0;
        Double commissionPercent = amountBinding.getCommission() != null ? amountBinding.getCommission() : 0.0;
        Double travellingCost = amountBinding.getTravelling_cost() != null ? amountBinding.getTravelling_cost() : 0.0;
        Double workersCost = amountBinding.getWorkers_cost() != null ? amountBinding.getWorkers_cost() : 0.0;

        // Step 2: Calculate base amount
        Double amount = weight * rate;

        // Step 3: Calculate commission
        Double commission = amount * (commissionPercent / 100);

        // Step 4: Calculate interest based on number of months since upfront date
        Double interest = 0.0;
        if (amountTakenUpfront > 0 && amountBinding.getAmount_taken_date() != null) {
            LocalDate takenDate = amountBinding.getAmount_taken_date();
            LocalDate currentDate = LocalDate.now();
            long monthsBetween = ChronoUnit.MONTHS.between(takenDate, currentDate);
            // Interest: 2% per ₹1,00,000 per month

            interest = amountTakenUpfront * 0.02 * monthsBetween;
        }

        // Step 5: Calculate final billing amount
        Double result = amount - (amountTakenUpfront + commission + travellingCost + workersCost + interest);
        return Math.round(result * 100.0) / 100.0;
    }
}
