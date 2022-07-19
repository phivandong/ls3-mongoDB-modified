package com.pvdong.ls3mongodb.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document(collection = "horse")
@Getter
@Setter
public class Horse {
    @Id
    private String id;

    @Field(name = "name")
    private String name;

    @Field(name = "foaled")
    private Date foaled;

    public Horse(String name, Date foaled) {
        this.name = name;
        this.foaled = foaled;
    }
}
