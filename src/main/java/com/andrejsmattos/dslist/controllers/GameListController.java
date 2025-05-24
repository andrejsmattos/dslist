package com.andrejsmattos.dslist.controllers;

import com.andrejsmattos.dslist.dtos.GameDTO;
import com.andrejsmattos.dslist.dtos.GameListDTO;
import com.andrejsmattos.dslist.dtos.GameMinDTO;
import com.andrejsmattos.dslist.dtos.ReplacementDTO;
import com.andrejsmattos.dslist.services.GameListService;
import com.andrejsmattos.dslist.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/lists")
public class GameListController {

    @Autowired
    private GameListService gameListService;

    @Autowired
    private GameService gameService;

    @GetMapping
    public List<GameListDTO> findAll() {
        List<GameListDTO> result = gameListService.findAll();
        return result;
    }

    @GetMapping(value = "/{listId}/games")
    public List<GameMinDTO> findByList(@PathVariable Long listId) {
        List<GameMinDTO> result = gameService.findByGameList(listId);
        return result;
    }

    @PostMapping(value = "/{listId}/replacement")
    public void move(@PathVariable Long listId, @RequestBody ReplacementDTO body) {
        gameListService.move(listId, body.getSourceIndex(), body.getDestinationIndex());
    }
}
