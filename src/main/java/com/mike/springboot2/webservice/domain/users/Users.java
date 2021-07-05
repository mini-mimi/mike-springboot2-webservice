package com.mike.springboot2.webservice.domain.users;

import com.mike.springboot2.webservice.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Getter
@NoArgsConstructor
@Entity
public class Users extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long users_uuid;

    @Column(nullable = false)
    private String users_name;

    @Column(nullable = false)
    private String users_email;

    @Column(nullable = false)
    private String users_id;

    @Column
    private String users_picture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role users_role;

    @Builder
    public Users(String users_name, String users_email, String users_id, String users_picture, Role users_role) {
        this.users_name = users_name;
        this.users_email = users_email;
        this.users_id = users_id;
        this.users_picture = users_picture;
        this.users_role = users_role;
    }

    public Users update(String users_name, String users_picture) {
        this.users_name = users_name;
        this.users_picture = users_picture;

        return this;
    }

    public String getRoleKey() {
        return this.users_role.getKey();
    }

}
