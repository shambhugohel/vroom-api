package com.carzhub.app.archtest;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.fields;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

/**
 * AdditionalArchitectureTest
 * <p>
 * Extra architecture checks that complement existing Taikai checks. Each rule
 * includes a short
 * comment explaining its purpose.
 *
 */
public class AdditionalArchitectureTest {

  private final JavaClasses importedClasses = new ClassFileImporter().importPackages(
      "com.carzhub..");

  // -------------------------
  // Controllers must be public and named properly
  // -------------------------
  // Enforce controllers to be annotated with @RestController, be public and end
  // with "Controller".
  // Public controllers are the conventional choice for Spring and avoid
  // proxy/visibility surprises.
  @Test
  void controllers_should_be_public_and_follow_naming() {
    classes()
        .that().areAnnotatedWith(RestController.class)
        .should().bePublic()
        .andShould().haveSimpleNameEndingWith("Controller")
        .check(importedClasses);
  }

  // -------------------------
  // Services should be annotated and follow naming
  // -------------------------
  // Require @Service on service classes and enforce naming like "XxxService" or
  // "XxxServiceImpl".
  @Test
  void services_should_be_annotated_and_named() {
    classes()
        .that().resideInAPackage("..service..")
        .and().areNotInterfaces()
        .should().beAnnotatedWith(Service.class)
        .andShould().haveNameMatching(".+(Service|ServiceImpl)")
        .check(importedClasses);
  }

  // -------------------------
  // Prevent package cycles
  // -------------------------
  // Break cyclic dependencies across feature packages to keep modules
  // independently maintainable.
  @Test
  void slices_should_be_free_of_cycles() {
    // matches com.carzhub.<slice>.. and ensures no cyclic dependencies between
    // slices
    slices().matching("com.carzhub.(*)..")
        .should().beFreeOfCycles()
        .check(importedClasses);
  }

  // -------------------------
  // DTOs should be effectively immutable
  // -------------------------
  // DTOs (in ..dto.. packages) are easier to reason about when fields are final.
  // This rule enforces that DTO classes only have final instance fields (no
  // setters implied).
  @Test
  void dtos_should_have_only_final_fields() {
    fields()
        .that().areDeclaredInClassesThat().resideInAPackage("..model..")
        .should().beFinal()
        .check(importedClasses);
  }

  // -------------------------
  // Exception classes naming
  // -------------------------
  // Any class that extends java.lang.Exception or RuntimeException should end
  // with "Exception"
  // This makes exception types immediately recognizable.
  @Test
  void exceptions_should_end_with_exception() {
    classes()
        .that().areAssignableTo(Throwable.class)
        .and().haveSimpleNameNotEndingWith("Error") // skip plain Errors (optional)
        .and().areNotInterfaces()
        .should().haveSimpleNameEndingWith("Exception")
        .check(importedClasses);
  }

  // -------------------------
  // Disallow System.out/err usage (encourage logging)
  // -------------------------
  // Direct usage of System.out/err is noisy in server apps; prefer structured
  // logging.
  @Test
  void no_system_out_or_err_should_be_used() {
    noClasses()
        .should().accessField(System.class, "out")
        .andShould().accessField(System.class, "err")
        .check(importedClasses);
  }

  // -------------------------
  // No test-only dependencies in main code
  // -------------------------
  // Avoid importing JUnit APIs from production classes
  @Test
  void production_code_should_not_depend_on_junit() {
    noClasses()
        .that().resideInAPackage("com.carzhub..") // production namespace
        .and().haveSimpleNameNotEndingWith("Test")
        .and().haveSimpleNameNotEndingWith("Tests")
        .and().resideOutsideOfPackage("..test..")
        .should().dependOnClassesThat()
        .resideInAnyPackage("org.junit..", "org.assertj..", "org.mockito..")
        .check(importedClasses);
  }

  // -------------------------
  // Avoid methods declaring generic Exception in signature
  // -------------------------
  // Prevent methods from declaring or throwing plain Exception — prefer specific
  // exceptions.
  @Test
  void methods_should_not_declare_generic_exception() {
    methods()
        .that().areDeclaredInClassesThat().resideInAPackage("com.carzhub..")
        .should().notDeclareThrowableOfType(Exception.class)
        .check(importedClasses);
  }
}
