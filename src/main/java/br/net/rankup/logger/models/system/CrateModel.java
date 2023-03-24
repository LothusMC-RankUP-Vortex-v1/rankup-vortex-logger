package br.net.rankup.logger.models.system;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CrateModel {

    private String crate;
    private String item;
    private long date;

}
