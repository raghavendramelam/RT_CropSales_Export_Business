package com.raghava.rt.bindings;

import lombok.Data;

import java.time.LocalDate;
@Data
public class ResultBinding {

    private String name;
    private String location;
    private Long mobile;
    private LocalDate crop_sold_date;
    private Double total_in_kgs;
    private Double rate_sold_to;
    private Integer total_bags_brought;
    private Double commission;
    private Double calculated_amount;
    private LocalDate crop_brought_date;
    private Double amount_taken_upfront;
    private LocalDate amount_taken_date;
    private Double travelling_cost;
    private Double workers_cost;
    private Boolean crop_in_storage;
    private LocalDate storage_date;
    private Double storage_amount_per_day;
}
