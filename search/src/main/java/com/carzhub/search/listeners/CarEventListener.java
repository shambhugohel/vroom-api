package com.carzhub.search.listeners;

import com.carzhub.events.CarCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Component;

@Component
public class CarEventListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(CarEventListener.class);

    @ApplicationModuleListener
    public void on(CarCreatedEvent event) {
        LOGGER.info("Received CarCreatedEvent: {} - {} {}. Indexing...",
                event.carId(), event.manufacturer(), event.model());

        // Simulate indexing logic
        // Implementation would likely use a SearchRepository to save to Elasticsearch
    }
}
