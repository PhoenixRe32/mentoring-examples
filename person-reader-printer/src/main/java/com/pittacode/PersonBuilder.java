package com.pittacode;

import java.io.IOException;

public class PersonBuilder {

    private final IntegerReader ageReader;
    private final IntegerReader yearReader;
    private final IntegerReader heightReader;
    private final StringReader nameReader;

    public PersonBuilder(IntegerReader ageReader, IntegerReader yearReader, IntegerReader heightReader, StringReader nameReader) {
        this.ageReader = ageReader;
        this.yearReader = yearReader;
        this.heightReader = heightReader;
        this.nameReader = nameReader;
    }

    public Person build() throws IOException {
        String name = nameReader.read();
        int yearOfBirth = yearReader.read();
        int age = ageReader.read();
        int height = heightReader.read();

        Person person = new Person(name, yearOfBirth, age, height);

        return person;
    }
}
