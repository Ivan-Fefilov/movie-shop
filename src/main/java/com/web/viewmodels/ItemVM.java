package com.web.viewmodels;

import com.domain.core.Item;
import com.web.viewmodels.binding.Param;

import javax.servlet.http.HttpServletRequest;

public class ItemVM extends AbstractVM {
    long id;
    @Param
    String name;
    @Param
    String producer;
    @Param
    String actors;
    @Param
    String production;
    @Param
    String quality;
    @Param
    String year;
    @Param
    String genre;
    @Param
    String price;
    @Param
    String description;
    @Param(nullable = true)
    String imageName;

    public ItemVM() {
        super();
    }

    public ItemVM(HttpServletRequest request) {
        super(request);
    }

    public ItemVM(Item item) {
        name = item.getName();
        producer = item.getProducer();
        actors = item.getActors();
        production = item.getProduction();
        quality = item.getQuality();
        year = item.getYear();
        genre = item.getGenre();
        price = item.getPrice();
        description = item.getDescription();
        imageName = item.getImage();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getProduction() {
        return production;
    }

    public void setProduction(String production) {
        this.production = production;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Item toItemModel() {
        return new Item(name, producer, actors, production, quality, year,
                genre, price, description, imageName);
    }
}
