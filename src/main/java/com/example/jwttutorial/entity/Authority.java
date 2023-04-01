package com.example.jwttutorial.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Authority {
    @Id
    @Column(name ="authority_name")
    private String authorityName;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "authority")
    List<MemberAuthority> memberAuthorityList = new ArrayList<>();
}
