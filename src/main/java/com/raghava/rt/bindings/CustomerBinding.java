package com.raghava.rt.bindings;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CustomerBinding {

    private String name;
    private String gender;
    private Integer age;
    private String location;
    private Long mobile;
    private String email;
    private LocalDate crop_brought_date;
    private String crop_type;
    private Integer total_bags_brought;
    private Double amount_taken_upfront;
    private LocalDate amount_taken_date;
}
