package com.raghava.rt.controller;

import com.raghava.rt.bindings.AmountBinding;
import com.raghava.rt.bindings.CustomerBinding;
import com.raghava.rt.bindings.CustomerSearchBinding;
import com.raghava.rt.bindings.ResultBinding;
import com.raghava.rt.entity.Customer;
import com.raghava.rt.service.ICustService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.text.DecimalFormat;
import java.util.List;

@RestController
public class CustController {
    @Autowired
    private ICustService iCustService;
    @PostMapping("/save")
    public ResponseEntity<String> registerdata( @RequestBody CustomerBinding customerBinding ){
        boolean b = iCustService.registerCustomer(customerBinding);
        if(b){
            return new ResponseEntity<>("Customer Registered", HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>("Registration Failed",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/calculate")
    public ResponseEntity<ResultBinding> calculate( @RequestBody AmountBinding amountBinding ){


        return new ResponseEntity<>( iCustService.calcuatedAmount(amountBinding),HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<List<Customer>> searchCustomers(@RequestBody CustomerSearchBinding searchBinding) {
        List<Customer> customers = iCustService.searchCustomers(searchBinding);

        if (customers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(customers);
    }
}
