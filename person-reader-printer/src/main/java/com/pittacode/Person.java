package com.pittacode;

public class Person {

    private final String name;
    private final int yearOfBirth;
    private final int age;
    private final int height;

    public Person(String name, int yearOfBirth, int age, int height) {
        this.name = name;
        this.yearOfBirth = yearOfBirth;
        this.age = age;
        this.height = height;
    }

    @Override
    public String toString() {
        return name + " is " + height + "cm tall and was born in " + yearOfBirth + " and is " + age + " years old.";
    }
}
