package com.training.drinkservice.controller;


import com.training.drinkservice.dto.DrinkDTO;
import com.training.drinkservice.entities.Drink;
import com.training.drinkservice.service.DrinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drink")
public class DrinkController {

    @Autowired
    private final DrinkService drinkService;


    public DrinkController(DrinkService drinkService) {
        this.drinkService = drinkService;
    }

    @GetMapping("/alldrinks")
    public List<Drink> getAllDrinks(){
        return drinkService.getAllDrinks();
    }

    @GetMapping("/onedrink/{id}")
    public Drink getOneDrink(@PathVariable Long id){
        return drinkService.getOne(id);
    }

    @PostMapping("/insertdrink")
    public String createDrink(@RequestBody DrinkDTO drinkDTO){
        return drinkService.saveDrink(drinkDTO);
    }

}
