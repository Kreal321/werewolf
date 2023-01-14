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
public class Day implements Serializable {
    private String lastNightInfo;
    private Vote SGTVote;
    private Vote KickVote;
    private List<Message> messageList = new ArrayList<>();
    private List<Event> eventList = new ArrayList<>();

}
