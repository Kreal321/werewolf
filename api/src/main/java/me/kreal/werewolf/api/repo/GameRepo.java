package me.kreal.werewolf.api.repo;

import me.kreal.werewolf.api.domain.Game;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GameRepo extends MongoRepository<Game, String> {


}
