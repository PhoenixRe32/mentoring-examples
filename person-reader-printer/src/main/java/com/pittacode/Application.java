package com.pittacode;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException {
        IntegerReader ageReader = new IntegerReader("age", 0, 150);
        IntegerReader yearReader = new IntegerReader("year of birth", 1900, 2021);
        IntegerReader heightReader = new IntegerReader("height", 30, 300);
        StringReader nameReader = new StringReader("name");

        PersonBuilder personBuilder = new PersonBuilder(ageReader, yearReader, heightReader, nameReader);

        Person person = personBuilder.build();
        System.out.println(person.toString());
    }
}
