package com.training.drinkservice.service;

import com.training.drinkservice.dto.DrinkDTO;
import com.training.drinkservice.entities.Drink;
import com.training.drinkservice.repository.DrinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class DrinkService {

    @Autowired
    private final DrinkRepository repository;


    public DrinkService(DrinkRepository repository) {
        this.repository = repository;
    }

    public String saveDrink(@RequestBody DrinkDTO drinkDTO){
        Drink drink = new Drink();
        drink.setName(drinkDTO.getName());
        drink.setType(drinkDTO.getType());
        drink.setPrice(drinkDTO.getPrice());
        drink.setSize(drinkDTO.getSize());
        drink.setRegion(drinkDTO.getRegion());
        drink.setPercentAlcohol(drinkDTO.getPercentAlcohol());

        repository.save(drink);

        return "The drink was created";
    }

    public List<Drink> getAllDrinks(){
        return repository.findAll();
    }

    public Drink getOne(Long id){
        return repository.findById(id).orElseThrow();
    }


}
