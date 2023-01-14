package me.kreal.werewolf.api.service;

import me.kreal.werewolf.api.domain.Game;
import me.kreal.werewolf.api.domain.Room;
import me.kreal.werewolf.api.repo.GameRepo;
import me.kreal.werewolf.api.repo.RoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
public class RoomService {

    private final RoomRepo roomRepo;

    @Autowired
    public RoomService(RoomRepo roomRepo) {
        this.roomRepo = roomRepo;
    }

    public Optional<Room> createNewRoom(String name) {

        if (roomRepo.findByName(name).isPresent()) return Optional.empty();

        Room room = new Room(null, name, null, new Date());

        return Optional.of(roomRepo.save(room));
    }

    public void updateGameId(Room room, String gameId) {

        room.setGameId(gameId);
        roomRepo.save(room);

    }

    public Optional<Room> findRoomByName(String name) {
        return roomRepo.findByName(name);
    }


}
