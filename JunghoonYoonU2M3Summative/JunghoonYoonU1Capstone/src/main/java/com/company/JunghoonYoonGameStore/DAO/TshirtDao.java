package com.company.JunghoonYoonGameStore.DAO;

import com.company.JunghoonYoonGameStore.DTO.TShirt;

import java.util.List;

public interface TshirtDao {
     TShirt addTShirt(TShirt t_shirt);

     TShirt getTShirt(Integer t_Shirt_Id);

     List<TShirt> getAllTShirts();

     void updateTShirt(TShirt tShirt);

     void deleteTShirt(Integer t_Shirt_Id);

     List<TShirt> getTShirtsByColor(String color);

     List<TShirt> getTShirtsBySize(String size);
}
