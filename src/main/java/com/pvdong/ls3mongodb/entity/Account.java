package com.pvdong.ls3mongodb.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "account")
@Getter
@Setter
@NoArgsConstructor
public class Account {
    @Id
    private String id;

    @Field(name = "username")
    @Indexed(unique = true)
    private String username;

    @Field(name = "password")
    private String password;

    @Field(name = "status")
    private Boolean status;

    @Field(name = "isTrainer")
    private Boolean isTrainer;

    @Field(name = "listHorseId")
    private List<String> listHorseId;

    public Account(String username, String password, Boolean status, Boolean isTrainer, List<String> listHorseId) {
        this.username = username;
        this.password = password;
        this.status = status;
        this.isTrainer = isTrainer;
        this.listHorseId = listHorseId;
    }
}
