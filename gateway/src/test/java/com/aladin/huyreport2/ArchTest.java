package com.aladin.huyreport2;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.aladin.huyreport2");

        noClasses()
            .that()
                .resideInAnyPackage("com.aladin.huyreport2.service..")
            .or()
                .resideInAnyPackage("com.aladin.huyreport2.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..com.aladin.huyreport2.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
