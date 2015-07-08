package com.example.tundepeter.test.model;

public class Person {
    private String name;
    private String description;
    private String smallPictureUrl;
    private String largePictureUrl;

    public Person(String tempName, String tempDescription, String tempSmallPictureUrl, String tempLargePictureUrl) {
        name = tempName;
        description = tempDescription;
        smallPictureUrl = tempSmallPictureUrl;
        largePictureUrl = tempLargePictureUrl;
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

