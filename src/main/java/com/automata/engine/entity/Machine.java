package com.automata.engine.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Machine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String alphabet; // e.g., "0,1" or "a,b"

    @OneToMany(mappedBy = "machine", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<State> states;

    @OneToMany(mappedBy = "machine", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transition> transitions;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAlphabet() {
        return alphabet;
    }

    public void setAlphabet(String alphabet) {
        this.alphabet = alphabet;
    }

    public List<State> getStates() {
        return states;
    }

    public void setStates(List<State> states) {
        this.states = states;
    }

    public List<Transition> getTransitions() {
        return transitions;
    }

    public void setTransitions(List<Transition> transitions) {
        this.transitions = transitions;
    }
}
