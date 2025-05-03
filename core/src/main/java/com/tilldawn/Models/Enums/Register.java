package com.tilldawn.Models.Enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Register {
    USERNAME("^[A-Za-z][A-Za-z0-9_.-]{3,9}$"),
    PASSWORD("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*])[A-Za-z\\d!@#$%^&*]{6,12}$");
    private final String pattern;
    Register(String pattern) {
        this.pattern = pattern;
    }
    public String getPattern() {
        return pattern;
    }
    public boolean matches(String input) {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(input);
        return m.matches();
    }
}
