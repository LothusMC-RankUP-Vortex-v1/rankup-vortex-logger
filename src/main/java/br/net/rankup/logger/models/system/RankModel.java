package br.net.rankup.logger.models.system;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RankModel {

    private String rank;
    private double price;
    private double rubis;
    private long date;

}
