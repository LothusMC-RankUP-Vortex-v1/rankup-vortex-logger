package br.net.rankup.logger.models.server;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class KillsModel {

    private boolean isKill;
    private String location;
    private String name;
    private long date;

}
