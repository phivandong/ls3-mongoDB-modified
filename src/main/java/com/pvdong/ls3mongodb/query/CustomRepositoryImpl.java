package com.pvdong.ls3mongodb.query;

import com.pvdong.ls3mongodb.entity.Account;
import com.pvdong.ls3mongodb.entity.Horse;
import com.pvdong.ls3mongodb.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.DateOperators;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomRepositoryImpl implements CustomRepository {

    @Autowired
    private MongoTemplate mongoTemplate;
    
    @Autowired
    private AccountRepository accountRepository;

//                Aggregation.addFields().addField("year").withValue(DateOperators.dateOf("horses.foaled")
//                        .toString("%Y")).build(),
//                Aggregation.project(String.valueOf(Fields.field("horses.foaled"))).and(DateOperators.dateOf("foaled")
//                        .toString("%Y")).as("year"),

//                Aggregation.match(Criteria.where("listHorseId").elemMatch(Criteria.byExample(
//                        Aggregation.lookup(
//                                "horse",
//                                "listHorseId.$",
//                                "_id",
//                                "horse_id"
//                        )
//                        Aggregation.addFields().addField("year")
//                                .withValue(DateOperators.dateOf("foaled").toString("%Y")).build()))),
//                Aggregation.match(Criteria.where("horses").elemMatch(Criteria.where("year").is(year.toString())))

//        List<String> horseIdList = new ArrayList<>();
//
//        for (Account account : accounts.getMappedResults()) {
//            horseIdList.addAll(account.getListHorseId());
//        }

    @Override
    public List<Horse> getListHorse(String trainerId, Integer year) {
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
