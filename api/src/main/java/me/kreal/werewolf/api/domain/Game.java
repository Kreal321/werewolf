package me.kreal.werewolf.api.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "games")
public class Game implements Serializable {

    private String id;

    private String name;

    private String type;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    // Game Info

    private int numberOfPlayer;

    private List<Player> playerList = new ArrayList<>();

    private String result;

    private boolean needSGTVote;

    // 0 for day 0, positive for day, negative for night
    private int dayNum;

    private List<Day> dayList = new ArrayList<>();

}
