package com.automata.engine.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
// This is the Secret Sauce! It enforces DFA determinism at the database level.
// You cannot have two transitions leaving the same state with the same symbol.
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"machine_id", "from_state_id", "input_symbol"})
})
public class Transition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "input_symbol", nullable = false)
    private String inputSymbol;

    @ManyToOne
    @JoinColumn(name = "machine_id", nullable = false)
    @JsonIgnore
    private Machine machine;

    @ManyToOne
    @JoinColumn(name = "from_state_id", nullable = false)
    private State fromState;

    @ManyToOne
    @JoinColumn(name = "to_state_id", nullable = false)
    private State toState;

    // TODO: Right-click -> Generate -> Getters and Setters for all fields!

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInputSymbol() {
        return inputSymbol;
    }

    public void setInputSymbol(String inputSymbol) {
        this.inputSymbol = inputSymbol;
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    public State getFromState() {
        return fromState;
    }

    public void setFromState(State fromState) {
        this.fromState = fromState;
    }

    public State getToState() {
        return toState;
    }

    public void setToState(State toState) {
        this.toState = toState;
    }
}