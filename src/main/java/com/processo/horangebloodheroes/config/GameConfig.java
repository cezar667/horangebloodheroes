package com.processo.horangebloodheroes.config;

import com.processo.horangebloodheroes.model.OrangeBloodHero;
import com.processo.horangebloodheroes.model.OrangeBloodHeroesParty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class GameConfig {

    @Value("${config.party.number-players}")
    private Integer NUMBER_PLAYERS_PARTY;
    @Value("${config.hero.initial-health}")
    private Integer INITIAL_HEALTH_HERO;
    @Value("${config.hero.initial-mana}")
    private Integer INITIAL_MANA_HERO;

    @Bean
    public OrangeBloodHeroesParty party(){

        return new OrangeBloodHeroesParty(heroes(),0);
    }

    private List<OrangeBloodHero> heroes(){
        List<OrangeBloodHero> heroes = new ArrayList<OrangeBloodHero>();
        for (Integer i = 0; i < NUMBER_PLAYERS_PARTY; i++) {
            heroes.add(OrangeBloodHero.builder().id(i).mana(INITIAL_MANA_HERO).health(INITIAL_HEALTH_HERO).build());
        }

        return heroes;
    }
}
