package me.kreal.werewolf.api.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Player implements Serializable {

    private int userId;

    private String playerName;

    private String roleName;

    private List<Action> actionList = new ArrayList<>();

    private boolean isAlive;

    //candidate, withdraw, civilian
    private boolean SGTStatus;

    private boolean isSGT;

    private int score;

}
