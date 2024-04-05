package com.processo.horangebloodheroes.config;

import com.processo.horangebloodheroes.enums.CombatType;
import org.springframework.core.convert.converter.Converter;

public class StringToCombatTypeConverter implements Converter<String, CombatType> {
    @Override
    public CombatType convert(String source) {
        return CombatType.valueOf(source.toUpperCase());
    }
}
