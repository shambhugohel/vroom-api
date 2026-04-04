package com.carzhub.search.listeners;

import static org.assertj.core.api.Assertions.assertThatCode;

import com.carzhub.events.CarCreatedEvent;
import org.junit.jupiter.api.Test;

class CarEventListenerTest {

    private final CarEventListener carEventListener = new CarEventListener();

    @Test
    void on_shouldProcessEventWithoutException() {
        CarCreatedEvent event = new CarCreatedEvent(123L, "Toyota", "Camry", 2023);

        assertThatCode(() -> carEventListener.on(event))
                .doesNotThrowAnyException();
    }
}
