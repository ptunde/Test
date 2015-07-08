package com.example.tundepeter.test.model;

public class Person {
    private String name;
    private String description;
    private String smallPictureUrl;
    private String largePictureUrl;

    public Person(String name, String description, String smallPictureUrl, String largePictureUrl) {
        this.name = name;
        this.description = description;
        this.smallPictureUrl = smallPictureUrl;
        this.largePictureUrl = largePictureUrl;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getSmallPictureUrl() {
        return smallPictureUrl;
    }

    public String getLargePictureUrl() {
        return largePictureUrl;
    }
}

