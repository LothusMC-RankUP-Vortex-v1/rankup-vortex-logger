package br.net.rankup.logger.models.system;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StorableModel {

    private double amount;
    private double price;
    private long date;

}
