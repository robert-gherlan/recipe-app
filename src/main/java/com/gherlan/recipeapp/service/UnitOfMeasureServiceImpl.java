package com.gherlan.recipeapp.service;

import com.gherlan.recipeapp.model.UnitOfMeasure;
import com.gherlan.recipeapp.repository.UnitOfMeasureRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UnitOfMeasureServiceImpl extends AbstractServiceImpl<UnitOfMeasure, Long> implements UnitOfMeasureService {

    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository) {
        super(unitOfMeasureRepository);
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public Optional<UnitOfMeasure> findByDescription(String description) {
        return unitOfMeasureRepository.findByDescription(description);
    }
}
