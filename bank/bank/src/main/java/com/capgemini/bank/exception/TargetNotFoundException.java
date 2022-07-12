package com.capgemini.bank.exception;

import java.util.UUID;

public class TargetNotFoundException extends RuntimeException {
    public TargetNotFoundException(UUID id) {
        super("Could not find the target with the following id: " + id);
    }
}
