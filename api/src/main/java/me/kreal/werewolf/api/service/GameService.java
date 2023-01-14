package me.kreal.werewolf.api.service;

import me.kreal.werewolf.api.domain.Game;
import me.kreal.werewolf.api.domain.Room;
import me.kreal.werewolf.api.repo.GameRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
public class GameService {

    private final GameRepo gameRepo;
    private final RoomService roomService;

    @Autowired
    public GameService(GameRepo gameRepo, RoomService roomService) {
        this.gameRepo = gameRepo;
        this.roomService = roomService;
    }



    private Game generateNewGame(int numOfPlayer) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        Game game = new Game();
        game.setName(formatter.format(new Date()) + " 狼人杀");
        game.setType("狼人杀");
        game.setNumberOfPlayer(numOfPlayer);
        game.setNeedSGTVote(true);
        game.setDayNum(0);
        game.setResult("未开始");

        return game;
    }

    public Game createNewGame(int numOfPlayer, Room room) {

        Game game = generateNewGame(numOfPlayer);

        gameRepo.save(game);
        roomService.updateGameId(room, game.getId());

        return game;
    }

    public Optional<Game> findGameById(String id) {
        return gameRepo.findById(id);
    }



    public void newDay() {

        // check day num

        /* 指挥狼人：狼人请睁眼，狼人请相互确认身份。
        狼人请杀人（狼人请统一意见）
        狼人请闭眼。
         */

        /* 2、指挥女巫：女巫请睁眼。
        女巫，今天晚上Ta死了，
        你要救他吗？你要毒死谁吗？
        女巫请选择目标。
        女巫请闭眼。
        */

        /* 3、指挥预言家：预言家请睁眼。
        今天晚上你要验谁？
        这个人是（大拇指朝上为好人，朝下为坏人。）
        预言家请闭眼。
        */

        /* 4、指挥守卫：守卫请睁眼。
        守卫今天晚上你要守谁？（同守同救者死）
        守卫请闭眼。
         */


    }

}
