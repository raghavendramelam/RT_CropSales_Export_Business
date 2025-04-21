package com.raghava.rt.service;

import com.raghava.rt.bindings.*;
import com.raghava.rt.entity.Customer;

import java.util.List;

public interface ICustService {

    public boolean registerCustomer( CustomerBinding customerBinding );

    public ResultBinding calcuatedAmount( AmountBinding amountBinding );

    public String updatedetails( UpdatCustomerBinding updatCustomerBinding );
    public List<Customer> searchCustomers( CustomerSearchBinding binding);

    public boolean updateColdStorage(UpdatCustomerBinding updatCustomerBinding);

    public int daysInAC(Long mobile);


}
