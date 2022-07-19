package com.pvdong.ls3mongodb.repository;

import com.pvdong.ls3mongodb.entity.Account;

public interface AccountRepositoryBase {
    Account getAccountByTrainerId(String trainerId);
}
