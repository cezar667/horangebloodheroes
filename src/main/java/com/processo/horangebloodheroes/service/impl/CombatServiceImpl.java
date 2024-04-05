package com.processo.horangebloodheroes.service.impl;

import com.processo.horangebloodheroes.enums.CombatResult;
import com.processo.horangebloodheroes.enums.CombatType;
import com.processo.horangebloodheroes.exception.HeroOutOfCombatException;
import com.processo.horangebloodheroes.model.OrangeBloodHero;
import com.processo.horangebloodheroes.model.OrangeBloodHeroesParty;
import com.processo.horangebloodheroes.model.OrangeHeroesPartyResponse;
import com.processo.horangebloodheroes.service.CombatService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Log4j2
public class CombatServiceImpl implements CombatService {
    private final OrangeBloodHeroesParty party;

    private final Integer COMBAT_MELEE_WIN_HEALTH;
    private final Integer COMBAT_MELEE_LOSE_HEALTH;
    private final Integer COMBAT_SPELL_WIN_MANA;
    private final Integer COMBAT_SPELL_LOSE_MANA;
    private final Integer PARTY_MORALE_WIN;
    private final Integer PARTY_MORALE_LOSE;
    private final Integer PARTY_MORALE_RECOVERY;
    private final Integer HERO_HEALTH_RECOVERY;
    private final Integer HERO_MANA_RECOVERY;


    @Autowired
    public CombatServiceImpl(OrangeBloodHeroesParty party,
                             @Value("${config.party.recovery-morale}") Integer partyMoraleRecovery,
                             @Value("${config.hero.recovery-health}") Integer heroHealthRecovery,
                             @Value("${config.hero.recovery-mana}") Integer heroManaRecovery,
                             @Value("${config.turn.combat.morale.win}") Integer partyMoraleWin,
                             @Value("${config.turn.combat.morale.lose}") Integer partyMoraleLose,
                             @Value("${config.turn.combat.melee.win-health}") Integer combatMeleeWin,
                             @Value("${config.turn.combat.melee.lose-health}") Integer combatMeleeLose,
                             @Value("${config.turn.combat.spell.win-mana}") Integer combatSpellWin,
                             @Value("${config.turn.combat.spell.lose-mana}") Integer combatSpellLose) {
        this.party = party;
        this.COMBAT_MELEE_WIN_HEALTH = combatMeleeWin;
        this.COMBAT_MELEE_LOSE_HEALTH = combatMeleeLose;
        this.COMBAT_SPELL_WIN_MANA = combatSpellWin;
        this.COMBAT_SPELL_LOSE_MANA = combatSpellLose;
        this.PARTY_MORALE_WIN = partyMoraleWin;
        this.PARTY_MORALE_LOSE = partyMoraleLose;
        this.PARTY_MORALE_RECOVERY = partyMoraleRecovery;
        this.HERO_HEALTH_RECOVERY = heroHealthRecovery;
        this.HERO_MANA_RECOVERY = heroManaRecovery;
    }

    public void handleCombat(Integer heroId, CombatResult combatResult, CombatType combatType) throws HeroOutOfCombatException {
        OrangeBloodHero hero = this.party.getHeroes().stream()
                .filter(h -> h.getId().equals(heroId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Herói com o ID " + heroId + " não encontrado"));
        if(hero.getHealth() == 0 || hero.getMana() == 0) {
            log.info("O #SangueLaranja "
                    + heroId +
                    " não está em condições de combater no momento! " +
                    "health[" + hero.getHealth()+"] " +
                    "mana[" + hero.getMana() + "]");
            throw new HeroOutOfCombatException("o herói não está em condições de combater no momento");
        }
        log.info("O #SangueLaranja " + heroId + " entrou em combate contra o Necab!");
        switch (combatType){
            case MELEE -> {
                meleeCombat(hero, combatResult);
            }
            case SPELL -> {
                spellCombat(hero, combatResult);
            }
        }
    }

    public void meleeCombat(OrangeBloodHero hero, CombatResult combatResult ){
        switch (combatResult){
            case WIN -> {
                hero.computeHealth(COMBAT_MELEE_WIN_HEALTH);
                party.computeMorale(PARTY_MORALE_WIN);
                log.info("O #SangueLaranja " + hero.getId() + " atingiu Necab com um golpe!");
            }
            case LOSE -> {
                hero.computeHealth(COMBAT_MELEE_LOSE_HEALTH);
                party.computeMorale(PARTY_MORALE_LOSE);
                log.info("O #SangueLaranja " + hero.getId() + " foi atingido por Necab!");
            }
        }
    }
    public void spellCombat(OrangeBloodHero hero, CombatResult combatResult ){
        switch (combatResult){
            case WIN -> {
                hero.computeMana(COMBAT_SPELL_WIN_MANA);
                party.computeMorale(PARTY_MORALE_WIN);
                log.info("O #SangueLaranja " + hero.getId() + " conseguiu lançar um feitiço em Necab!");
            }
            case LOSE -> {
                hero.computeMana(COMBAT_SPELL_LOSE_MANA);
                party.computeMorale(PARTY_MORALE_LOSE);
                log.info("O #SangueLaranja " + hero.getId() + " tentou, mas Necab lançou o feitiço contra o feiticeiro!");
            }
        }
    }

    public OrangeHeroesPartyResponse statusParty(){
        return OrangeHeroesPartyResponse.builder()
                .party(this.party)
                .timestamp(Instant.now().getEpochSecond()).build();
    }

    @Scheduled(initialDelay = 10000, fixedRate = 10000)
    public void recoveryTurn(){
        log.info("Hora do descanso");

        this.party.computeMorale(PARTY_MORALE_RECOVERY);
        log.info("A moral dos heróis subiu " + PARTY_MORALE_RECOVERY + " pontos.");

        this.party.getHeroes().forEach(hero -> {
            hero.computeHealth(HERO_HEALTH_RECOVERY);
            hero.computeMana(HERO_MANA_RECOVERY);
        });
        log.info("Os Herois descansaram e ganharam " +
                + HERO_HEALTH_RECOVERY + " de health e " +
                + HERO_MANA_RECOVERY + " de mana.");
    }
}
