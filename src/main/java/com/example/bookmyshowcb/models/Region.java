package com.example.bookmyshowcb.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Region extends BaseModel{
    private String name;

    @OneToMany(mappedBy = "region")
    private List<Theatre> theatres;

}


// R : T
// 1 : M
// 1 : 1

// 1 : M
