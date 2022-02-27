package com.project.barterexchange;

public class User {
    public String name, city, area, railwayStation, email, id;

    public User() {
    }

    public User(String id, String email, String name, String city, String area, String railwayStation) {
        this.email = email;
        this.name = name;
        this.city = city;
        this.area = area;
        this.railwayStation = railwayStation;
    }
}
