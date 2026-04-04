package com.carzhub.app.archtest;

import static com.tngtech.archunit.core.domain.JavaModifier.FINAL;
import static com.tngtech.archunit.core.domain.JavaModifier.PRIVATE;
import static com.tngtech.archunit.core.domain.JavaModifier.STATIC;

import com.enofex.taikai.Taikai;
import com.enofex.taikai.spring.ConfigurationsConfigurer;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

/**
 * This class defines architectural and code-style constraints for the project
 * `com.carportal` using
 * the Taikai library (a wrapper over ArchUnit-like checks).
 * <p>
 * It ensures consistency, enforces naming conventions, prevents unwanted
 * dependencies, and
 * validates correct layering (controllers, services, repositories, etc.).
 */
public class ArchitectureTest {

        @Test
        void shouldFulfillConstraints() {
                Taikai.builder()
                                .namespace("com.carzhub") // The root package for all rules

                                // --- General Java-level rules ---
                                .java(java -> java
                                                // .noUsageOfDeprecatedAPIs() // Disallow usage of deprecated
                                                // methods/classes -
                                                // Disabled due to Hibernate 6 legacy support
                                                .methodsShouldNotDeclareGenericExceptions() // Prevent methods from
                                                                                            // using "throws Exception"
                                                                                            // —
                                                // encourages specific exception types
                                                .utilityClassesShouldBeFinalAndHavePrivateConstructor() // Utility/helper
                                                                                                        // classes must
                                                                                                        // be final
                                                // with private constructors
                                                .imports(imports -> imports
                                                                .shouldHaveNoCycles() // No cyclic dependencies between
                                                                                      // packages
                                                                .shouldNotImport("..shaded..") // Avoid using shaded
                                                                                               // (repackaged) libraries
                                                                .shouldNotImport(
                                                                                "org.junit..")) // Application code
                                                                                                // should not import
                                                                                                // JUnit (testing
                                                                                                // library)
                                                .naming(naming -> naming
                                                                .classesShouldNotMatch(
                                                                                ".*Implpl") // Disallow suspicious class
                                                                                            // names ending with
                                                                                            // "Implpl" (probably a
                                                                // typo or bad naming)
                                                                .methodsShouldNotMatch(
                                                                                "^(foo$|bar$).*") // Disallow
                                                                                                  // placeholder or
                                                                                                  // dummy methods named
                                                                                                  // "foo" or "bar"
                                                                .fieldsShouldNotMatch(
                                                                                ".*(List|Set|Map)$") // Discourage
                                                                                                     // naming fields
                                                                                                     // directly after
                                                                                                     // collection types
                                                                // (e.g., "userList" → better name like "users")
                                                                .fieldsShouldMatch("com.enofex.taikai.Matcher",
                                                                                "matcher") // Fields matching certain
                                                                                           // pattern should align with
                                                                                           // `Matcher`
                                                                // convention
                                                                .interfacesShouldNotHavePrefixI())) // Interfaces should
                                                                                                    // not start with
                                                                                                    // "I" (e.g.,
                                // "IUserService" → bad)

                                // --- Logging conventions ---
                                .logging(logging -> logging
                                                .loggersShouldFollowConventions(
                                                                Logger.class, "LOGGER",
                                                                List.of(PRIVATE, FINAL, STATIC)))
                                // Each class should have a private static final logger named "LOGGER"
                                // Example: private static final Logger logger =
                                // LoggerFactory.getLogger(MyClass.class);

                                // --- Testing rules ---
                                .test(test -> test
                                                .junit(junit -> junit
                                                                .classesShouldNotBeAnnotatedWithDisabled() // Avoid
                                                                                                           // @Disabled
                                                                                                           // on test
                                                                                                           // classes
                                                                .methodsShouldNotBeAnnotatedWithDisabled())) // Avoid
                                                                                                             // @Disabled
                                                                                                             // on test
                                                                                                             // methods

                                // --- Spring-specific architectural rules ---
                                .spring(spring -> spring
                                                .noAutowiredFields() // Enforce constructor injection over @Autowired
                                                                     // fields

                                                .boot(boot -> boot
                                                                .springBootApplicationShouldBeIn("com.carzhub"))
                                                // The @SpringBootApplication class must reside in the root package

                                                // --- Configuration classes ---
                                                .configurations(ConfigurationsConfigurer::namesShouldEndWithConfiguration)
                                                // Configuration classes must end with "Configuration"

                                                // --- Controllers layer ---
                                                .controllers(controllers -> controllers
                                                                .shouldBeAnnotatedWithRestController() // All
                                                                                                       // controllers
                                                                                                       // must have
                                                                                                       // @RestController
                                                                .namesShouldEndWithController() // Controller classes
                                                                                                // should end with
                                                                                                // "Controller"
                                                                .shouldNotDependOnOtherControllers()) // Prevent
                                                                                                      // controllers
                                                                                                      // from depending
                                                                                                      // on other
                                                // controllers (layer violation)

                                                // --- Services layer ---
                                                .services(services -> services
                                                                // .shouldBeAnnotatedWithService() // Must use @Service
                                                                // annotation - Disabled for interfaces
                                                                .shouldNotDependOnControllers() // Services should not
                                                                                                // depend on controllers
                                                                                                // (maintains
                                                                // clean architecture)
                                                                .namesShouldMatch(
                                                                                ".+(Service|ServiceImpl)")) // Service
                                                                                                            // class
                                                                                                            // names
                                                                                                            // must end
                                                                                                            // with
                                                                                                            // "Service"

                                                // --- Repository layer ---
                                                .repositories(repositories -> repositories
                                                                .shouldBeAnnotatedWithRepository() // Must use
                                                                                                   // @Repository
                                                                                                   // annotation
                                                                .shouldNotDependOnServices() // Repositories should not
                                                                                             // depend on services
                                                                                             // (maintains
                                                                // separation)
                                                                .namesShouldEndWithRepository())) // Repository class
                                                                                                  // names must end with
                                                                                                  // "Repository"

                                // --- Build and execute all checks ---
                                .build()
                                .check();
        }
}
