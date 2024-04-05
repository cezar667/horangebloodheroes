package com.processo.horangebloodheroes.model;

import com.processo.horangebloodheroes.utils.AttributeLimitUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class OrangeHeroesPartyResponse {
    private OrangeBloodHeroesParty party;
    private Long timestamp;

}
