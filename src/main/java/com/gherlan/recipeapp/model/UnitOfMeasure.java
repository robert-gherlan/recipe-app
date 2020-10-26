package com.gherlan.recipeapp.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "units_of_measure")
public class UnitOfMeasure extends BaseEntity {

    @Column(name = "description", nullable = false)
    private String description;
}
