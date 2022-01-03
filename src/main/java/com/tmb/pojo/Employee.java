package com.tmb.pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder(setterPrefix = "set")
@Getter
public class Employee {

    private int id;
    @Setter
    private String fname;
    private String lname;
}
