package br.net.rankup.logger.models.system;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SpawnerModel {

    private String action;
    private String type;
    private double amount;
    private long date;

}
