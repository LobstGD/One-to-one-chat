package com.example.websocket_one_to_one.user;

import jakarta.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "chat_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nickName;
    private String fullName;
    private Status status;
}
