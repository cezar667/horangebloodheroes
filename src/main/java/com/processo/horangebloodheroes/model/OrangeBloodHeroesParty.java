package com.processo.horangebloodheroes.model;

import com.processo.horangebloodheroes.utils.AttributeLimitUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class OrangeBloodHeroesParty {
    private List<OrangeBloodHero> heroes;
    private Integer morale;

    public void computeMorale(Integer morale){
        this.morale = AttributeLimitUtils.limit(this.morale + morale,0,1000);
    }
}
