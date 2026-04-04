package com.carzhub.communication.listeners;

import com.carzhub.events.CarCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Component;

@Component
public class CommunicationEventListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommunicationEventListener.class);

    @ApplicationModuleListener
    public void on(CarCreatedEvent event) {
        LOGGER.info("Received CarCreatedEvent: {}. Sending welcome email to seller...", event.carId());
        // Implementation would send actual email
    }
}
