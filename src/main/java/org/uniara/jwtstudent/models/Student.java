package org.uniara.jwtstudent.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter @Setter
public class Student {
    @Id
    private String id;
    private String name;
    private String email;
    private float grade1;
    private float grade2;
    private float average;
}
