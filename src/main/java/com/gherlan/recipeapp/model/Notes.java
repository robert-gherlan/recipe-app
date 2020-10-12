package com.gherlan.recipeapp.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "notes")
public class Notes extends BaseEntity {

    @Lob
    @Column(name = "recipe_notes", nullable = false)
    private String recipeNotes;

    @OneToOne
    private Recipe recipe;
}
