package me.legrange.escl.discovery;

public final class DiscoveryException extends Exception {
    private DiscoveryException(String message) {
        super(message);
    }

    DiscoveryException(String message, Throwable cause) {
        super(message, cause);
    }
}
