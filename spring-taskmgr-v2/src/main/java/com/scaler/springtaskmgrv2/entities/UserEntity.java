package com.scaler.springtaskmgrv2.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class UserEntity extends BaseEntity {

    @Column(unique = true, nullable = false, length = 50)
    String username;
    String password; // TODO: Hash this
    String email;
    String bio;
    String image;

}
