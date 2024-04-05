package com.processo.horangebloodheroes.model;

import com.processo.horangebloodheroes.utils.AttributeLimitUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class OrangeBloodHero {
    private Integer id;
    private Integer health;
    private Integer mana;

    public void computeHealth(Integer health){
        this.health = AttributeLimitUtils.limit(this.health + health,0,100);
    }
    public void computeMana(Integer mana){
        this.mana = AttributeLimitUtils.limit(this.mana + mana,0,100);
    }
}
