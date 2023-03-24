package br.net.rankup.logger.models.server;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ItemDropModel {

    private String owner;
    private String location;
    private String itemName;
    private Integer itemID;
    private int amount;
    private long date;

}
