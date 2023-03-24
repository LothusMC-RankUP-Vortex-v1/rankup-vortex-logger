package br.net.rankup.logger.models.server;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CommandModel {

    private String owner;
    private String world;
    private long date;
    private String message;

}
