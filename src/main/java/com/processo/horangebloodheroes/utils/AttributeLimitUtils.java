package com.processo.horangebloodheroes.utils;

public class AttributeLimitUtils {
    public static int limit(int value, int min, int max) {
        return Math.min(Math.max(value, min), max);
    }
}

