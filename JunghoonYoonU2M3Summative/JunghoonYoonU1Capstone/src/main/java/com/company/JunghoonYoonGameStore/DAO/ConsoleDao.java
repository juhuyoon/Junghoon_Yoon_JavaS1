package com.company.JunghoonYoonGameStore.DAO;

import java.util.List;

public interface ConsoleDao {
    com.company.JunghoonYoonGameStore.DTO.Console addConsole(com.company.JunghoonYoonGameStore.DTO.Console console);

    com.company.JunghoonYoonGameStore.DTO.Console getConsole(Integer console_id);

    List<com.company.JunghoonYoonGameStore.DTO.Console> getAllConsoles();

    void updateConsole(com.company.JunghoonYoonGameStore.DTO.Console console);

    void deleteConsole(Integer console_id);

    List<com.company.JunghoonYoonGameStore.DTO.Console> getConsolesByManufacturer(String manufacturer);
}
