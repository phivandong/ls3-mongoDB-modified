package com.pvdong.ls3mongodb.service;

import com.pvdong.ls3mongodb.dto.HorseDto;
import com.pvdong.ls3mongodb.entity.Horse;
import com.pvdong.ls3mongodb.repository.HorseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HorseServiceImpl implements HorseService {

    @Autowired
    HorseRepository horseRepository;

    @Override
    public List<Horse> findHorseList(String id, Integer year) {
        return horseRepository.getHorseListByTrainerIdAndYear(id, year);
    }

    @Override
    public List<Horse> findAllHorses() {
        return horseRepository.findAll();
    }

    @Override
    public Horse createHorse(HorseDto horseDto) {
        Horse horse = new Horse(horseDto.getName(), horseDto.getFoaled());
        horseRepository.save(horse);
        return horse;
    }

    @Override
    public Horse findHorseById(String id) {
        if (horseRepository.findById(id).isPresent()) {
            return horseRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public Horse update(String id, HorseDto horseDto) {
        Horse foundHorse = findHorseById(id);
        foundHorse.setName(horseDto.getName());
        foundHorse.setFoaled(horseDto.getFoaled());
        horseRepository.save(foundHorse);
        return foundHorse;
    }

    @Override
    public void deleteHorseById(String id) {
        horseRepository.deleteById(id);
    }
}
