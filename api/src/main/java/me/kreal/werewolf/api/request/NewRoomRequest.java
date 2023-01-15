package me.kreal.werewolf.api.request;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class NewRoomRequest {
    private String roomName;
}
