package com.pvdong.ls3mongodb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    private String username;
    private String password;
    private Boolean status;
    private Boolean isTrainer;
    private List<String> listHorseId;
}
