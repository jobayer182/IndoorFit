package com.example.indoorfit;

public class User {
    private String fullName;
    private String email;
    private int weight;
    private float height;
    private int age;

    // Default constructor (required for Firebase)
    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String fullName, String email, int weight, float height, int age) {
        this.fullName = fullName;
        this.email = email;
        this.weight = weight;
        this.height = height;
        this.age = age;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public int getWeight() {
        return weight;
    }

    public float getHeight() {
        return height;
    }

    public int getAge() {
        return age;
    }
}



