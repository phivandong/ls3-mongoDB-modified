package com.pvdong.ls3mongodb.repository;

import com.pvdong.ls3mongodb.entity.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends MongoRepository<Account, String>, AccountRepositoryBase {
    @Query("{'username': ?0}")
    Account findByUsername(String username);

    Account findAccountById(String id);
}
