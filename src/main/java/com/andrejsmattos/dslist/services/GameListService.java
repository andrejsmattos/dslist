package com.andrejsmattos.dslist.services;

import com.andrejsmattos.dslist.dtos.GameDTO;
import com.andrejsmattos.dslist.dtos.GameListDTO;
import com.andrejsmattos.dslist.dtos.GameMinDTO;
import com.andrejsmattos.dslist.entities.Game;
import com.andrejsmattos.dslist.entities.GameList;
import com.andrejsmattos.dslist.projections.GameMinProjection;
import com.andrejsmattos.dslist.repositories.GameListRepository;
import com.andrejsmattos.dslist.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GameListService {

    @Autowired
    private GameListRepository gameListRepository;

    @Autowired
    private GameRepository gameRepository;

    @Transactional(readOnly = true)
    public List<GameListDTO> findAll() {
        List<GameList> result = gameListRepository.findAll();
        List<GameListDTO> dto = result.stream().map(gameList -> new GameListDTO(gameList)).toList();
        return dto;
    }

    @Transactional
    public void move(Long listId, int sourceIndex, int destinationIndex) {

        List<GameMinProjection> list = gameRepository.searchByList(listId);

        GameMinProjection obj = list.remove(sourceIndex);
        list.add(destinationIndex, obj);

        int min = sourceIndex < destinationIndex ? sourceIndex : destinationIndex;
        int max = sourceIndex < destinationIndex ? destinationIndex:sourceIndex;

        for (int i = min; i <= max; i++) {
            gameListRepository.updateBelongingPosition(listId, list.get(i).getId(), i);}
    }
}
