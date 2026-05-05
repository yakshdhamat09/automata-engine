package com.automata.engine.controller;

import com.automata.engine.dto.SimulateRequest;
import com.automata.engine.entity.Machine;
import com.automata.engine.entity.State;
import com.automata.engine.entity.Transition;
import com.automata.engine.repository.MachineRepository;
import com.automata.engine.service.MachineService;
import com.automata.engine.service.SimulationResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/machines")
@CrossOrigin(origins = "*") // Allows your frontend to connect safely
public class MachineController {

    private final MachineService machineService;
    private final MachineRepository machineRepository;

    // Spring wires everything up automatically
    public MachineController(MachineService machineService, MachineRepository machineRepository) {
        this.machineService = machineService;
        this.machineRepository = machineRepository;
    }

    // ENDPOINT 1: Get all machines (Lightweight list for the UI dropdown)
    @GetMapping
    public List<Machine> getAllMachines() {
        return machineRepository.findAll();
    }

    // ENDPOINT 2: Get a specific machine's full configuration
    @GetMapping("/{id}")
    public ResponseEntity<Machine> getMachine(@PathVariable Long id) {
        return machineRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ENDPOINT 3: Save a new machine to the database
    @PostMapping
    public Machine createMachine(@RequestBody Machine machine) {

        // 1. Tell every State who its parent Machine is
        if (machine.getStates() != null) {
            for (State state : machine.getStates()) {
                state.setMachine(machine);
            }
        }

        // 2. Tell every Transition who its parent Machine is...
        if (machine.getTransitions() != null && machine.getStates() != null) {
            for (Transition transition : machine.getTransitions()) {
                transition.setMachine(machine);

                // THE FIX: Point the transition's states to the EXACT Java objects in our main list
                // This prevents Hibernate from thinking they are brand new, disconnected states.
                for (State state : machine.getStates()) {
                    if (state.getName().equals(transition.getFromState().getName())) {
                        transition.setFromState(state);
                    }
                    if (state.getName().equals(transition.getToState().getName())) {
                        transition.setToState(state);
                    }
                }
            }
        }
        return machineRepository.save(machine);
    }

    // ENDPOINT 4: The Simulation Workhorse
    @PostMapping("/{id}/simulate")
    public SimulationResult simulateMachine(@PathVariable Long id, @RequestBody SimulateRequest request) {
        // Hands the ID and the String off to your math engine
        return machineService.simulate(id, request.inputString());
    }
}