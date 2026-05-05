package com.automata.engine.service;

import java.util.List;

public record SimulationResult(String status, List<String> trace) {
}