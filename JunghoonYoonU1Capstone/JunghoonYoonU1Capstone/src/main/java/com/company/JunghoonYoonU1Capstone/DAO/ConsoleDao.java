package com.company.JunghoonYoonU1Capstone.DAO;

import java.util.List;

public interface ConsoleDao {
    com.company.JunghoonYoonU1Capstone.DTO.Console addConsole(com.company.JunghoonYoonU1Capstone.DTO.Console console);

    com.company.JunghoonYoonU1Capstone.DTO.Console getConsole(Integer console_id);

    List<com.company.JunghoonYoonU1Capstone.DTO.Console> getAllConsoles();

    void updateConsole(com.company.JunghoonYoonU1Capstone.DTO.Console console);

    void deleteConsole(Integer console_id);

    List<com.company.JunghoonYoonU1Capstone.DTO.Console> getConsolesByManufacturer(String manufacturer);
}
