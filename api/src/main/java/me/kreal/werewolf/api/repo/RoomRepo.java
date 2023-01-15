package me.kreal.werewolf.api.repo;

import me.kreal.werewolf.api.domain.Game;
import me.kreal.werewolf.api.domain.Room;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoomRepo extends MongoRepository<Room, String> {

    Optional<Room> findByRoomName(String name);

}
