package com.softcraft.ohhsansibackend.utils;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class UniqueCodeGenerator {
    private static final String ALPHANUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public String generate() {
        StringBuilder code = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            int index = random.nextInt(ALPHANUMERIC.length());
            code.append(ALPHANUMERIC.charAt(index));
        }
        return code.toString();
    }
}