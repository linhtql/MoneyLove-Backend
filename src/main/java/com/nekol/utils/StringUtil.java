package com.nekol.utils;

import java.util.UUID;

public class StringUtil {

    public static String generateCode() {
        UUID uuid = UUID.randomUUID();

        return "TRANSID-" + uuid.toString().toUpperCase();
    }
}
