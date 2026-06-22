package com.bank.util;

public final class IdGenerator {

    private static int counter = 1000;

    private IdGenerator() {
    }

    public static synchronized String nextAccountId() {
        counter++;
        return "ACC" + counter;
    }

    public static synchronized String nextTransactionId() {
        return "TXN" + System.currentTimeMillis() + "-" + counter;
    }

    public static void reset() {
        counter = 1000;
    }
}
