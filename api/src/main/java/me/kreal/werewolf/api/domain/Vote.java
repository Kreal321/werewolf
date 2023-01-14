package me.kreal.werewolf.api.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

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
public class Vote implements Serializable {

    // completed, in progress, terminated, not started
    private String status;

    private String result;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date time;

    // -2: campaign for SGT ,-1: dead ,0: abstain, >0: player num
    private List<Integer> voteResultList = new ArrayList<>();
}
