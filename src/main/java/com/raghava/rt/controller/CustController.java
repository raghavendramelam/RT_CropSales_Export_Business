package com.raghava.rt.controller;

import com.raghava.rt.bindings.*;
import com.raghava.rt.entity.Customer;
import com.raghava.rt.service.ICustService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
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

    @PostMapping("/update")
    public ResponseEntity<String> update( @RequestBody UpdatCustomerBinding updatCustomerBinding ){

        if(iCustService.updateColdStorage(updatCustomerBinding)){
            return new ResponseEntity<>("Customer Details are updated",HttpStatus.OK);
        }

        return new ResponseEntity<>("Records not found",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/getDays")
    public ResponseEntity<String> getDays( @PathVariable Long mobile ){

        return new ResponseEntity<>("Days in Cold Storage and total cost from initial day "+iCustService.daysInACandCost(mobile),HttpStatus.FOUND);
    }

    @PostMapping("/email")
    public ResponseEntity<String> sendReceiptEmail(@RequestBody ResultBinding resultBinding) {
        String body = "Receipt Details:\n" + resultBinding.toString(); // or format it nicely
        iCustService.sendReceiptAsEmail(resultBinding.getEmail(), "Your Receipt", body);
        return ResponseEntity.ok("Receipt sent via email successfully!");
    }
}
