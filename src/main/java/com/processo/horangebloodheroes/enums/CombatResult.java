package com.processo.horangebloodheroes.enums;

import lombok.Getter;

@Getter
public enum CombatResult {
    WIN("win"),
    LOSE("lose");

    private final String name;
    CombatResult(String name) {
        this.name = name;
    }
}
