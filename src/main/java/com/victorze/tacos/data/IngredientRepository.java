package com.victorze.tacos.data;

import org.springframework.data.repository.CrudRepository;

import com.victorze.tacos.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}
