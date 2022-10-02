package me.legrange.escl.rest;

public final class EsclException extends Exception {

    EsclException(String message) {
        super(message);
    }

    EsclException(String message, Throwable cause) {
        super(message, cause);
    }
}
