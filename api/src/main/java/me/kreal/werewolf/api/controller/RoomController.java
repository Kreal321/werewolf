package me.kreal.werewolf.api.controller;

import me.kreal.werewolf.api.domain.Game;
import me.kreal.werewolf.api.domain.Room;
import me.kreal.werewolf.api.dto.MessageDto;
import me.kreal.werewolf.api.request.NewRoomRequest;
import me.kreal.werewolf.api.response.DataResponse;
import me.kreal.werewolf.api.service.GameService;
import me.kreal.werewolf.api.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/room")
public class RoomController {

    private final SimpMessagingTemplate messagingTemplate;
    private final RoomService roomService;


    @Autowired
    public RoomController(SimpMessagingTemplate messagingTemplate, RoomService roomService) {
        this.messagingTemplate = messagingTemplate;
        this.roomService = roomService;
    }

    @PostMapping("/new")
    public DataResponse getNewRoom(@Payload MessageDto messageDto, @RequestBody NewRoomRequest newRoomRequest) {

//        messageDto.setMessage("new game");
//        messagingTemplate.convertAndSend("/api/message/subscription/" + "123",messageDto);

        Optional<Room> roomOptional = roomService.createNewRoom(newRoomRequest.getRoomName());

        if (!roomOptional.isPresent()) {
            return DataResponse.builder().success(false).message("房间名称已经存在").build();
        }

        return DataResponse.builder().success(true).data(roomOptional.get()).build();
    }

    @GetMapping("/{roomName}")
    public DataResponse getRoomByRoomName(@Payload MessageDto messageDto, @PathVariable String roomName) {

        Optional<Room> roomOptional = roomService.findRoomByName(roomName);

        if (!roomOptional.isPresent()) {
            return DataResponse.builder().success(false).message("房间名称不存在").build();
        }

        return DataResponse.builder().success(true).data(roomOptional.get()).build();
    }

}
