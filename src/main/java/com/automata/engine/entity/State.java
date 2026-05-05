package com.automata.engine.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // e.g., "q0"

    private boolean isStart; // True if this is q0
    private boolean isAccepting; // True if this is in F

    @ManyToOne
    @JoinColumn(name = "machine_id", nullable = false)
    @JsonIgnore // Prevents infinite loops when outputting JSON later
    private Machine machine;

    // TODO: Right-click -> Generate -> Getters and Setters for all fields!

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

    public boolean isStart() {
        return isStart;
    }

    public void setStart(boolean start) {
        isStart = start;
    }

    public boolean isAccepting() {
        return isAccepting;
    }

    public void setAccepting(boolean accepting) {
        isAccepting = accepting;
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }
}
