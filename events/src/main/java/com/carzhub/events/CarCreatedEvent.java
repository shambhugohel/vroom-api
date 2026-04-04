package com.carzhub.events;

public record CarCreatedEvent(Long carId, String manufacturer, String model, int year) {
}
