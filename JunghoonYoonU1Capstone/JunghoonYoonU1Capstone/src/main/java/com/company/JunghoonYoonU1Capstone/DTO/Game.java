package com.company.JunghoonYoonU1Capstone.DTO;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

public class Game {
    private Integer game_id;
    @NotEmpty(message = "Supply a title")
    private String title;
    @NotEmpty(message = "Supply a rating")
    private String esrb_rating;
    @NotEmpty(message = "Supply a description")
    private String description;
    @NotNull(message = "Supply a price")
    private BigDecimal price;
    @NotEmpty(message = "Supply the studio name")
    private String studio;
    @NotNull(message = "Give the quantity of the product")
    @Min(value = 1)
    @Max(value = 11)
    private Integer quantity;

    public Integer getGame_id() {
        return game_id;
    }

    public void setGame_id(Integer game_id) {
        this.game_id = game_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEsrb_rating() {
        return esrb_rating;
    }

    public void setEsrb_rating(String esrb_rating) {
        this.esrb_rating = esrb_rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Objects.equals(game_id, game.game_id) &&
                title.equals(game.title) &&
                esrb_rating.equals(game.esrb_rating) &&
                description.equals(game.description) &&
                price.equals(game.price) &&
                studio.equals(game.studio) &&
                quantity.equals(game.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(game_id, title, esrb_rating, description, price, studio, quantity);
    }
}
