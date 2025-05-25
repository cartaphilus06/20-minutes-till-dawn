package com.tilldawn.Models.Enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum CheatCodes {
    LESSEN_TIME("decrease time","time cheat: "),
    LESSEN_ARBITRARY_TIME("decrease time (?<amount>.*)","time cheat: "),
    ADD_LEVEL("add level","level cheat: "),
    ADD_HP("add hp","hp cheat: "),
    INFINITE_HP("infinite hp","hp cheat: "),;
    private final String pattern;
    private final String description;
    CheatCodes(String pattern,String description) {
        this.pattern = pattern;
        this.description = description;
    }
    public Matcher getMatcher(String input) {
        Pattern p = Pattern.compile(pattern);
        return p.matcher(input);
    }
    public String getDescription() {
        return description;
    }
    public String getPattern() {
        return pattern;
    }
}
