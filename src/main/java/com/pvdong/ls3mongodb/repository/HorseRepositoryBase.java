package com.pvdong.ls3mongodb.repository;

import com.pvdong.ls3mongodb.entity.Horse;

import java.util.List;

public interface HorseRepositoryBase {
    List<Horse> getHorseListByTrainerIdAndYear(String trainerId, Integer year);
}
