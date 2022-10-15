package ru.sfu.model;

import javax.validation.constraints.*;

public class TableGame {
    int id;

    @NotNull(message = "should not be empty")
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 3, max = 30, message="Name should  be between 3 and 30")
    String gameName;

    @Min(value = 1, message = "price should be greater than 0")
    @Max(value = 9000, message = "price should be less than 9000")
    int price;

    @NotNull
    @Min(value = 1, message = "player amount should be greater than 0")
    @Max(value = 20, message = "player amount should be less than 20")
    int playerAmount;

    @NotEmpty(message = "Genre should not be empty")
    @Size(min = 3, max = 30, message="Genre should  be between 3 and 30")
    String genre;

    public TableGame(){}

    public TableGame(int id, String gamename, int price, int playerAmount, String genre) {
        this.id = id;
        this.gameName = gamename;
        this.price = price;
        this.playerAmount = playerAmount;
        this.genre = genre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPlayerAmount() {
        return playerAmount;
    }

    public void setPlayerAmount(int playerAmount) {
        this.playerAmount = playerAmount;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString(){
        return "id: " + id + ", name: " + gameName + ", price: " + price + ", playersAmount: " + playerAmount + ", genre: " + genre;
    }
}
