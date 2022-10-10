package com.tunts;

import lombok.*;

@Data
@Builder
public class Student {

    private int matriculation;
    private String name;
    private Integer absences;
    private Integer p1;
    private Integer p2;
    private Integer p3;
    private String situation;
    private Integer naf;

}
