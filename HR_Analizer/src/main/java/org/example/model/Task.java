package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;



@Getter
@AllArgsConstructor
@ToString
public class Task {



    private String name;
    private String project;
    private double duration;
    private String user;
    private Date date;

}
