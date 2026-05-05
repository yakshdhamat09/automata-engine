package com.automata.engine.service;

import com.automata.engine.entity.Machine;
import com.automata.engine.entity.State;
import com.automata.engine.entity.Transition;
import com.automata.engine.repository.MachineRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service // Tells Spring this class handles core business logic
public class MachineService {

    private final MachineRepository machineRepository;

    // Spring automatically injects the repository here
    public MachineService(MachineRepository machineRepository) {
        this.machineRepository = machineRepository;
    }

    public SimulationResult simulate(Long machineId, String inputString) {
        // 1. Fetch the Machine
        Machine machine = machineRepository.findById(machineId)
                .orElseThrow(() -> new RuntimeException("Machine not found!"));

        List<String> trace = new ArrayList<>();

        // 2. Find the Start State (q0)
        State currentState = null;
        for (State state : machine.getStates()) {
            if (state.isStart()) {
                currentState = state;
                break;
            }
        }

        if (currentState == null) {
            return new SimulationResult("ERROR: No start state defined.", trace);
        }

        trace.add(currentState.getName());

        // 3. The Traversal Loop O(n)
        // Convert the input string (e.g., "1001") into a character array and loop through it
        for (char symbol : inputString.toCharArray()) {
            String currentInput = String.valueOf(symbol);

            // Check if the symbol is even in the alphabet
            if (!machine.getAlphabet().contains(currentInput)) {
                trace.add("INVALID SYMBOL: " + currentInput);
                return new SimulationResult("REJECTED", trace);
            }

            // Find the exact transition leaving the current state using this symbol
            State nextState = null;
            for (Transition transition : machine.getTransitions()) {
                if (transition.getFromState().getId().equals(currentState.getId())
                        && transition.getInputSymbol().equals(currentInput)) {
                    nextState = transition.getToState();
                    break; // Determinism guarantees only one valid path, so we can break early
                }
            }

            // 4. Dead State Check
            if (nextState == null) {
                trace.add("DEAD END at " + currentInput);
                return new SimulationResult("REJECTED", trace);
            }

            // Move the machine forward
            currentState = nextState;
            trace.add(currentState.getName());

        }

        // 5. Resolution (Are we in an accepting state, F?)
        if (currentState.isAccepting()) {
            return new SimulationResult("ACCEPTED", trace);
        } else {
            return new SimulationResult("REJECTED", trace);
        }
    }
}
