package com.processo.horangebloodheroes.controller;

import com.processo.horangebloodheroes.enums.CombatResult;
import com.processo.horangebloodheroes.enums.CombatType;
import com.processo.horangebloodheroes.exception.HeroOutOfCombatException;
import com.processo.horangebloodheroes.model.OrangeBloodHeroesParty;
import com.processo.horangebloodheroes.model.OrangeHeroesPartyResponse;
import com.processo.horangebloodheroes.service.CombatService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class OrangeBloodHeroesControllerTest {

    @Mock
    CombatService combatService;

    @InjectMocks
    OrangeBloodHeroesController controller;

    @Test
    void combat_ValidRequest_ReturnsAcceptedResponse() throws HeroOutOfCombatException {
        int heroId = 1;
        CombatResult combatResult = CombatResult.WIN;
        CombatType combatType = CombatType.MELEE;

        Mockito.doNothing().when(combatService).handleCombat(heroId, combatResult, combatType);

        ResponseEntity<String> response = controller.combat(heroId, combatResult, combatType);

        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals("o combate é válido e foi computado com sucesso.", response.getBody());
        Mockito.verify(combatService, Mockito.times(1)).handleCombat(heroId, combatResult, combatType);
    }

    @Test
    void getParty_ValidRequest_ReturnsOkResponse() {
        OrangeHeroesPartyResponse partyResponse = new OrangeHeroesPartyResponse(OrangeBloodHeroesParty.builder().build(), 1l);

        Mockito.when(combatService.statusParty()).thenReturn(partyResponse);

        ResponseEntity<OrangeHeroesPartyResponse> response = controller.getParty();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(partyResponse, response.getBody());
        Mockito.verify(combatService, Mockito.times(1)).statusParty();
    }
}