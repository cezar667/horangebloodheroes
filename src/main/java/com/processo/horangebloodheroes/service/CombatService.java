package com.processo.horangebloodheroes.service;

import com.processo.horangebloodheroes.enums.CombatResult;
import com.processo.horangebloodheroes.enums.CombatType;
import com.processo.horangebloodheroes.exception.HeroOutOfCombatException;
import com.processo.horangebloodheroes.model.OrangeBloodHero;
import com.processo.horangebloodheroes.model.OrangeHeroesPartyResponse;

public interface CombatService {
    public void handleCombat(Integer heroId, CombatResult combatResult, CombatType combatType) throws HeroOutOfCombatException;

    public void meleeCombat(OrangeBloodHero hero, CombatResult combatResult );
    public void spellCombat(OrangeBloodHero hero, CombatResult combatResult );
    public OrangeHeroesPartyResponse statusParty();
    public void recoveryTurn();
}
