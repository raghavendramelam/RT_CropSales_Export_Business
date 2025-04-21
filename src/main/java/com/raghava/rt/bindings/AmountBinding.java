package com.raghava.rt.bindings;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AmountBinding {

    private Long mobile;
    private Double total_in_kgs;

    private Double rate_sold_to;
    private LocalDate crop_sold_date;

    private Integer total_bags;
    private Double Commission;
    private Double travelling_cost;
    private Double workers_cost;
    private Double amount_taken_upfront;
    private LocalDate amount_taken_date;
   private Boolean crop_in_storage;


}

