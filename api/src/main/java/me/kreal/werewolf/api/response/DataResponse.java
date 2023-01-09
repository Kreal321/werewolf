package me.kreal.werewolf.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataResponse {
    private boolean success;
    private String message;
    private Object data;
}
