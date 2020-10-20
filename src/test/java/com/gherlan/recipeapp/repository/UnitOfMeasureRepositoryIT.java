package com.gherlan.recipeapp.repository;

import com.gherlan.recipeapp.model.UnitOfMeasure;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
class UnitOfMeasureRepositoryIT {

    @Autowired
    private UnitOfMeasureRepository unitOfMeasureRepository;

    @Test
    void findByDescription() {
        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
        Assertions.assertThat(unitOfMeasureOptional.get().getDescription()).isEqualTo("Teaspoon");
    }

    @Test
    void findByDescriptionCup() {
        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Cup");
        Assertions.assertThat(unitOfMeasureOptional.get().getDescription()).isEqualTo("Cup");
    }
}    