package org.uniara.jwtstudent.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter @Setter
public class User {
    @Id
    private String id;
    private String email;
    private String password;
}
