package com.processo.horangebloodheroes.controller;

import com.processo.horangebloodheroes.enums.CombatResult;
import com.processo.horangebloodheroes.enums.CombatType;
import com.processo.horangebloodheroes.exception.HeroOutOfCombatException;
import com.processo.horangebloodheroes.model.OrangeHeroesPartyResponse;
import com.processo.horangebloodheroes.service.CombatService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class OrangeBloodHeroesController {
    private final CombatService combatService;

    @Autowired
    public OrangeBloodHeroesController(CombatService combatService) {
        this.combatService = combatService;
    }

    @GetMapping("/combat")
    public ResponseEntity<String> combat(
           @RequestParam Integer heroId,
            @RequestParam CombatResult combatResult,
            @RequestParam CombatType combatType) throws HeroOutOfCombatException {
        combatService.handleCombat(heroId, combatResult, combatType);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("o combate é válido e foi computado com sucesso.");
    }

    @GetMapping("/party")
    public ResponseEntity<OrangeHeroesPartyResponse> getParty() {
        return ResponseEntity.ok(combatService.statusParty());
    }

}
