package com.example.bookmyshowcb.models;

import com.example.bookmyshowcb.models.enums.Feature;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Screen extends BaseModel {
    private String name;

    @OneToMany
    private List<Seat> seats;

    @Enumerated
    @ElementCollection
    private List<Feature> features;
    // automatically mapping table is made for you.

    // Class to class -> normal cardinalities
    // Class to enum -> always ManyToOne -> enumerated
    // Class to list of enums -> always ManyToMany -> enumerated and collection

    // feature Table
    // Id   |   enum
    // 1    |   DOLBY
    // 2    |   THREE_D
    // 3    |   FOUR_K

    // screenId  featureId
    // 1         1
    // 1         2
    // 10        1
    // 10        2
    // 10        3
    // 11        3
}
