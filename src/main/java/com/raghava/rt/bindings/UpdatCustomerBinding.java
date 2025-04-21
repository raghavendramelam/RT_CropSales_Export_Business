package com.raghava.rt.bindings;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdatCustomerBinding {


    private Boolean crop_in_storage;
    private LocalDate storage_date;
    private String storage_name;

    private Double amount_per_day;

}
