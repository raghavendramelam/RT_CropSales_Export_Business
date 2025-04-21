package com.raghava.rt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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
    private Boolean crop_in_storage;
    private LocalDate storage_date;
    private String storage_name;
    private Double storage_amount_per_day;
    private LocalDate crop_sold_date;
    private Double total_in_kgs;
    private Double rate_sold_to;
    private Double commission;
    private Double calculated_amount;
    private String crop_sold_to;
    private Double travelling_cost;
    private Double workers_cost;
    @CreationTimestamp
    private LocalDate creation;
    @UpdateTimestamp
    private LocalDate updatation;

}
