package com.andrejsmattos.dslist.repositories;

import com.andrejsmattos.dslist.entities.Game;
import com.andrejsmattos.dslist.entities.GameList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameListRepository extends JpaRepository <GameList, Long> {
}
