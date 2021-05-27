package com.pittacode;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        PersonBuilder personBuilder = new PersonBuilder();
        Person person = personBuilder.build();
        System.out.println(person.toString());
    }
}
