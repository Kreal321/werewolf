package me.kreal.werewolf.api.controller;

import jdk.nashorn.internal.runtime.options.Option;
import me.kreal.werewolf.api.domain.Game;
import me.kreal.werewolf.api.domain.Room;
import me.kreal.werewolf.api.dto.MessageDto;
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
@RequestMapping("/game")
public class GameController {

    private final SimpMessagingTemplate messagingTemplate;
    private final GameService gameService;
    private final RoomService roomService;

    @Autowired
    public GameController(SimpMessagingTemplate messagingTemplate, GameService gameService, RoomService roomService) {
        this.messagingTemplate = messagingTemplate;
        this.gameService = gameService;
        this.roomService = roomService;
    }


    @GetMapping("/new")
    public DataResponse getNewGame(@Payload MessageDto messageDto, @RequestParam int numOfPlayer, @RequestParam String roomName) {

//        messageDto.setMessage("new game");
//        messagingTemplate.convertAndSend("/api/message/subscription/" + "123",messageDto);

        Optional<Room> roomOptional = roomService.findRoomByName(roomName);

        if (!roomOptional.isPresent()) {
            return DataResponse.builder().success(false).message("房间名称不存在").build();
        }

        Game game = gameService.createNewGame(numOfPlayer, roomOptional.get());

        return DataResponse.builder().success(true).data(game).build();
    }

    @GetMapping("/{id}")
    public DataResponse getGameById(@PathVariable String id, @Payload MessageDto messageDto) {

        Optional<Game> gameOptional = gameService.findGameById(id);

        if (!gameOptional.isPresent()) {
            return DataResponse.builder().success(false).message("这场游戏ID不存在").build();
        }

        return DataResponse.builder().success(true).data(gameOptional.get()).build();
    }

}
