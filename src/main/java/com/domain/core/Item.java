package com.domain.core;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ITEM")
public class Item extends AbstractModel {

    private String name;
    private String producer;
    private String actors;
    private String production;
    private String quality;
    private String year;
    private String genre;
    private String price;
    private String description;
    private String image;
    private List<ShopItem> shopItems = new ArrayList<>();

    protected Item() {
    }

    public Item(String name, String producer, String actors, String production, String quality,
                String year, String genre, String price, String description, String image) {
        this.name = name;
        this.producer = producer;
        this.actors = actors;
        this.production = production;
        this.quality = quality;
        this.year = year;
        this.genre = genre;
        this.price = price;
        this.description = description;
        this.image = image;
    }

    @Column(unique = true, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(nullable = false)
    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    @Column(nullable = false)
    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    @Column(nullable = false)
    public String getProduction() {
        return production;
    }

    public void setProduction(String production) {
        this.production = production;
    }

    @Column(nullable = false)
    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    @Column(nullable = false)
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Column(nullable = false)
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Column(nullable = false)
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Column(columnDefinition = "long varchar", nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(nullable = false)
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    // TODO: 14.12.2016 Check fetch and cascade
    @OneToMany(mappedBy = "item", fetch = FetchType.EAGER)
    @Cascade(CascadeType.ALL)
    public List<ShopItem> getShopItems() {
        return shopItems;
    }

    public void setShopItems(List<ShopItem> shopItems) {
        this.shopItems = shopItems;
    }

    @Transient
    public int getCount() {
        int c = 0;
        for (ShopItem si : shopItems) {
            c += si.getCount();
        }
        return c;
    }

    @Transient
    public List<Shop> getShops() {
        List<Shop> shops = new ArrayList<>();
        for (ShopItem si : shopItems) {
            shops.add(si.getShop());
        }
        return shops;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;

        Item item = (Item) o;

        return name.equals(item.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
