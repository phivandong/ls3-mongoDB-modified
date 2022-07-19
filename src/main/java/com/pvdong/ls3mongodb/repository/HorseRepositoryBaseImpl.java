package com.pvdong.ls3mongodb.repository;

import com.pvdong.ls3mongodb.entity.Account;
import com.pvdong.ls3mongodb.entity.Horse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.DateOperators;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.ArrayList;
import java.util.List;

public class HorseRepositoryBaseImpl implements HorseRepositoryBase {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<Horse> getHorseListByTrainerIdAndYear(String trainerId, Integer year) {
        Account account = accountRepository.getAccountByTrainerId(trainerId);
        assert account != null;
        List<String> horseIdList = account.getListHorseId();

        List<Horse> results = new ArrayList<>();

        for (String horseId : horseIdList) {
            Aggregation aggregation = Aggregation.newAggregation(
                    Aggregation.match(Criteria.where("_id").is(horseId)),
                    Aggregation.addFields().addField("year")
                            .withValue(DateOperators.dateOf("foaled").toString("%Y")).build(),
                    Aggregation.match(Criteria.where("year").is(year.toString()))
            );
            AggregationResults<Horse> horses = mongoTemplate.aggregate(aggregation, "horse", Horse.class);
            results.addAll(horses.getMappedResults());
        }
        return results;
    }
}
