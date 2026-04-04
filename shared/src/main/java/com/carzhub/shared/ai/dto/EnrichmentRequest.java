package com.carzhub.shared.ai.dto;

public record EnrichmentRequest(
        String carManufacturer,
        String carModel,
        int manufacturedYear,
        String description) {
}
