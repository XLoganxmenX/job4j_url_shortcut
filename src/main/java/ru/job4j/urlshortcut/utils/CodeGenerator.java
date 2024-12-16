package ru.job4j.urlshortcut.utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.UUID;

public class CodeGenerator {
    public static String generateCodeFrom(String name) {
        String guid = UUID.nameUUIDFromBytes(name.getBytes()).toString();
        return guid.split("-")[0];
    }

    public static String generatePassword() {
        return RandomStringUtils.randomAscii(12)
                .replace(" ", "");
    }
}
