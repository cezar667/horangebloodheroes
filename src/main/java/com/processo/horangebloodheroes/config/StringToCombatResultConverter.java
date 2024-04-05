package com.processo.horangebloodheroes.config;

import com.processo.horangebloodheroes.enums.CombatResult;
import org.springframework.core.convert.converter.Converter;

public class StringToCombatResultConverter implements Converter<String, CombatResult> {
    @Override
    public CombatResult convert(String source) {
        return CombatResult.valueOf(source.toUpperCase());
    }
}
