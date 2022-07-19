package com.pvdong.ls3mongodb.service;

import com.pvdong.ls3mongodb.dto.HorseDto;
import com.pvdong.ls3mongodb.entity.Horse;

import java.util.List;

public interface HorseService {
    List<Horse> findHorseList(String id, Integer year);
    List<Horse> findAllHorses();
    Horse createHorse(HorseDto horseDto);
    Horse findHorseById(String id);
    Horse update(String id, HorseDto horseDto);
    void deleteHorseById(String id);
}
