package com.example.jwttutorial.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="member")
@Getter
@Setter
public class Member {
    @JsonIgnore
    @Id
    @Column(name="member_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, unique = true)
    private String membername;
    @JsonIgnore
    @Column(length = 100)
    private String password;
    @Column(length = 50)
    private String nickname;
    @JsonIgnore
    @Column()
    private boolean activated;

    @OneToMany(mappedBy = "member")
    private List<MemberAuthority> memberAuthorityList = new ArrayList<>();
}
