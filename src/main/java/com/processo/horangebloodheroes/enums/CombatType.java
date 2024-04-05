package com.processo.horangebloodheroes.enums;

import lombok.Getter;

@Getter
public enum CombatType {
    MELEE("melee"),
    SPELL("spell");

    private final String name;
    CombatType(String name) {
        this.name = name;
    }
}
