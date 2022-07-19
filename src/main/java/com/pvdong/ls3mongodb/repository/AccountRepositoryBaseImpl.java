package com.pvdong.ls3mongodb.repository;

import com.pvdong.ls3mongodb.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;

public class AccountRepositoryBaseImpl implements AccountRepositoryBase {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Account getAccountByTrainerId(String trainerId) {
        Aggregation agg = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("_id").is(trainerId)),
                Aggregation.match(Criteria.where("isTrainer").is(true))
        );
        AggregationResults<Account> accounts = mongoTemplate.aggregate(agg, "account", Account.class);
        return accounts.getUniqueMappedResult();
    }
}
