package com.pittacode.obstruction;

@FunctionalInterface
public interface ConsoleTokenisedInputSupplier {

    String[] get() throws Exception;
}
