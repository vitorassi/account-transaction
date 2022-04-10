package com.anderson.adapter.database.entity;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;


@Builder
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String documentNumber;

}
