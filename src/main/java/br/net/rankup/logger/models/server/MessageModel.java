package br.net.rankup.logger.models.server;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MessageModel {

    private String owner;
    private long date;
    private String message;

}
