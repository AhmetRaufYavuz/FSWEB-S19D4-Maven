package com.workintech.s19d1.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotNull(message = "Film Name Can not be null")
    private String name;

    @Column(name = "director_name")
    @NotNull
    private String directorName;



    private Integer rating;



    @Column(name = "release_date")
    private LocalDate releaseDate;



    @ManyToMany(cascade = {CascadeType.DETACH,
            CascadeType.MERGE,CascadeType.PERSIST,
            CascadeType.REFRESH},
            fetch = FetchType.EAGER)
    @JoinTable(name = "movie_actor",
            inverseJoinColumns  = @JoinColumn(name = "actor_id"),
             joinColumns = @JoinColumn(name = "movie_id"))
    private List<Actor> actors;


    public void addActor(Actor actor){
        if (actors == null) {
            actors = new ArrayList<>();
        }
        actors.add(actor);
    }

}
