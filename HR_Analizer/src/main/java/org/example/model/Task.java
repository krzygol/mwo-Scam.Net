package org.example.model;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;



@Getter
@AllArgsConstructor
public class Task {



    private String name;
    private String project;
    private double duration;
    private String owner;
    private Date date;

}
