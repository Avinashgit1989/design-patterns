package com.learnwithak.userservice.command.api.data;

import com.learningwithak.commonservice.model.CardDetails;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    private String userId;
    private String firstName;
    private String lastName;
    @Embedded
    private CardDetails cardDetails;
}
