package com.processo.horangebloodheroes.service.impl;

import com.processo.horangebloodheroes.enums.CombatResult;
import com.processo.horangebloodheroes.enums.CombatType;
import com.processo.horangebloodheroes.exception.HeroOutOfCombatException;
import com.processo.horangebloodheroes.model.OrangeBloodHero;
import com.processo.horangebloodheroes.model.OrangeBloodHeroesParty;
import com.processo.horangebloodheroes.model.OrangeHeroesPartyResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CombatServiceImplTest {

    @Mock
    OrangeBloodHeroesParty party;

    CombatServiceImpl combatService;

    private OrangeBloodHero heroMock;
    @BeforeEach
    void setUp() {
        heroMock = new OrangeBloodHero(0, 100, 100);
        combatService = new CombatServiceImpl(party,1,1,1,1,1,1,1,1,1);
    }


    @Test
    void handleCombat_ValidHeroAndResult_CallsAppropriateCombatMethod() throws HeroOutOfCombatException {
        when(party.getHeroes()).thenReturn(Collections.singletonList(heroMock));

        combatService.handleCombat(heroMock.getId(), CombatResult.WIN, CombatType.MELEE);

        Mockito.verify(party, Mockito.times(1)).getHeroes();

    }

    @Test
    void handleCombat_InvalidHero_ThrowsException() {
        when(party.getHeroes()).thenReturn(Collections.emptyList());

        assertThrows(IllegalArgumentException.class,
                () -> combatService.handleCombat(1, CombatResult.WIN, CombatType.MELEE));
    }
    @Test
    void handleCombat_InvalidHero_ThrowsHeroOutOfCombatException() {
        when(party.getHeroes()).thenReturn(Collections.singletonList(OrangeBloodHero.builder().id(0).mana(0).health(0).build()));

        assertThrows(HeroOutOfCombatException.class,
                () -> combatService.handleCombat(0, CombatResult.WIN, CombatType.MELEE));
    }

    @Test
    void meleeCombat_Win_CorrectlyAdjustsHeroHealthAndPartyMorale() {
        int initialHealth = heroMock.getHealth();
        int initialMorale = party.getMorale();
        CombatResult combatResult = CombatResult.WIN;

        combatService.meleeCombat(heroMock, combatResult);

        assertEquals(initialHealth, heroMock.getHealth());
        assertEquals(initialMorale, party.getMorale());
    }

    @Test
    void meleeCombat_Lose_CorrectlyAdjustsHeroHealthAndPartyMorale() {
        int initialHealth = heroMock.getHealth();
        int initialMorale = party.getMorale();
        CombatResult combatResult = CombatResult.LOSE;

        combatService.meleeCombat(heroMock, combatResult);

        assertEquals(initialHealth, heroMock.getHealth());
        assertEquals(initialMorale, party.getMorale());
    }

    @Test
    void spellCombat_Win_CorrectlyAdjustsHeroManaAndPartyMorale() {
        int initialMana = heroMock.getMana();
        int initialMorale = party.getMorale();
        CombatResult combatResult = CombatResult.WIN;

        combatService.spellCombat(heroMock, combatResult);

        assertEquals(initialMana, heroMock.getMana());
        assertEquals(initialMorale, party.getMorale());
    }

    @Test
    void spellCombat_Lose_CorrectlyAdjustsHeroManaAndPartyMorale() {
        int initialMana = heroMock.getMana();
        int initialMorale = party.getMorale();
        CombatResult combatResult = CombatResult.LOSE;

        combatService.spellCombat(heroMock, combatResult);

        assertEquals(initialMana, heroMock.getMana());
        assertEquals(initialMorale, party.getMorale());
    }

    @Test
    void statusParty_ReturnsCorrectPartyResponse() {
        List<OrangeBloodHero> heroes = Arrays.asList(heroMock, heroMock);
        when(party.getHeroes()).thenReturn(heroes);

        OrangeHeroesPartyResponse response = combatService.statusParty();

        assertEquals(heroes, response.getParty().getHeroes());
    }

    @Test
    void recoveryTurn_AdjustsMoraleAndHeroAttributes() {
        int initialMorale = party.getMorale();
        int initialHealth = heroMock.getHealth();
        int initialMana = heroMock.getMana();

        combatService.recoveryTurn();

        assertEquals(initialMorale, party.getMorale());
        assertEquals(initialHealth, heroMock.getHealth());
        assertEquals(initialMana, heroMock.getMana());
    }
}
