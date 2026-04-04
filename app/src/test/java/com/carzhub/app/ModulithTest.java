package com.carzhub.app;

import com.carzhub.CarPortalApplication;
import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.test.ApplicationModuleTest;

class ModulithTest {

    @Test
    void verifyModulith() {
        ApplicationModules.of(CarPortalApplication.class).verify();
    }
}
