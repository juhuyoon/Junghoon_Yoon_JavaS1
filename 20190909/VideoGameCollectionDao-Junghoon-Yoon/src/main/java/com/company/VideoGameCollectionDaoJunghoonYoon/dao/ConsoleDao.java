package com.company.VideoGameCollectionDaoJunghoonYoon.dao;

import java.util.List;

public interface ConsoleDao {

    com.company.VideoGameCollectionDaoJunghoonYoon.model.Console addConsole(com.company.VideoGameCollectionDaoJunghoonYoon.model.Console console);

    com.company.VideoGameCollectionDaoJunghoonYoon.model.Console getConsole(int id);

    List<com.company.VideoGameCollectionDaoJunghoonYoon.model.Console> getAllConsoles();

    void updateConsole(com.company.VideoGameCollectionDaoJunghoonYoon.model.Console console);

    void deleteConsole(int id);

}
