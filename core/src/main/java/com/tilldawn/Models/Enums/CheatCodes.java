package com.tilldawn.Models.Enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum CheatCodes {
    LESSEN_TIME("^decrease time$"),
    LESSEN_ARBITRARY_TIME("^decrease time (?<amount>.*)$"),
    ADD_LEVEL("^add level$"),
    ADD_HP("^add hp$"),
    INFINITE_HP("^infinite hp$");
    private final String pattern;
    CheatCodes(String pattern) {
        this.pattern = pattern;
    }
    public Matcher getMatcher(String input) {
        Pattern p = Pattern.compile(pattern);
        return p.matcher(input);
    }
}
