package com.automata.engine.dto;

// A lightweight record to receive JSON like: { "inputString": "10010" }
public record SimulateRequest(String inputString) {
}
