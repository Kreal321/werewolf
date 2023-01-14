package me.kreal.werewolf.api.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Action implements Serializable {

    // nothing
    // kill, sleep
    // see, rescue, poison, fire, guard, duel
    private String did;

    // playerNum
    private int target;
}
