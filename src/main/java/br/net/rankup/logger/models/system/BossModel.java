package br.net.rankup.logger.models.system;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BossModel {

    private String boss;
    private String action;
    private double amountPrice;
    private long date;

}
