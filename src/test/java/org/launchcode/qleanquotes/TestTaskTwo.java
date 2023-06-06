package org.launchcode.qleanquotes;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.MappedSuperclass;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import static org.assertj.core.api.Fail.fail;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;


public class TestTaskTwo extends AbstractTest {

/* Verifying that AbstractEntity has MappedSuperclass annotation, has id field and GeneratedValue and Id annotations.
Customer and Quote models exist, and have their fields correctly */


    @Test
    public void testAbstractEntityHasCorrectAnnotation() throws ClassNotFoundException {
        Class abstractEntityClass = getClassByName(".models.AbstractEntity");
        Annotation annotation = abstractEntityClass.getAnnotation(MappedSuperclass.class);
        assertNotNull(annotation, "AbstractEntity must have @MappedSuperclass annotation");
    }

    @Test
    public void testIdFieldHasCorrectAnnotations() throws ClassNotFoundException {
        Class abstractEntityClass = getClassByName(".models.AbstractEntity");
        Field idField = null;
        try {
            idField = abstractEntityClass.getDeclaredField("id");
        } catch (NoSuchFieldException e) {
            Assertions.fail("AbstractEntity does not have an id field");
        }

//
//        Annotation idAnnotation = idField.getAnnotation(Id.class);
//        assertNotNull(idAnnotation, "id field must have @Id annotation");

        Annotation generatedValueAnnotation = idField.getAnnotation(GeneratedValue.class);
        assertNotNull(generatedValueAnnotation, "id field must have @GeneratedValue annotation");
    }


    @Test
    public void testCustomerHasNameField() throws ClassNotFoundException {
        Class customerClass = getClassByName(".models.Customer");
        Field nameField = null;
        try {
            nameField = customerClass.getDeclaredField("name");
        } catch (NoSuchFieldException e) {
            Assertions.fail("Customer class has no name field");
        }
    }

    @Test
    public void testCustomerHasLastNameField() throws ClassNotFoundException {
        Class customerClass = getClassByName(".models.Customer");
        Field lastNameField = null;
        try {
            lastNameField = customerClass.getDeclaredField("lastName");
        } catch (NoSuchFieldException e) {
            Assertions.fail("Customer class has no lastName field");
        }
    }

    @Test
    public void testCustomerHasEmailField() throws ClassNotFoundException {
        Class customerClass = getClassByName(".models.Customer");
        Field emailField = null;
        try {
            emailField = customerClass.getDeclaredField("email");
        } catch (NoSuchFieldException e) {
            Assertions.fail("Customer class has no email field");
        }
    }

    @Test
    public void testCustomerHasPasswordField() throws ClassNotFoundException {
        Class customerClass = getClassByName(".models.Customer");
        Field passwordField = null;
        try {
            passwordField = customerClass.getDeclaredField("password");
        } catch (NoSuchFieldException e) {
            Assertions.fail("Customer class has no password field");
        }
    }


    @Test
    public void testCustomerHasPHoneNumberField() throws ClassNotFoundException {
        Class customerClass = getClassByName(".models.Customer");
        Field phoneNumberField = null;
        try {
            phoneNumberField = customerClass.getDeclaredField("phoneNumber");
        } catch (NoSuchFieldException e) {
            Assertions.fail("Customer class has no phoneNumber field");
        }
    }

    @Test
    public void testQuoteHasSquareFeetField() throws ClassNotFoundException {
        Class quoteClass = getClassByName(".models.Quote");
        Field squareFeetField = null;
        try {
            squareFeetField = quoteClass.getDeclaredField("squareFeet");
        } catch (NoSuchFieldException e) {
            Assertions.fail("Quote class has no squareFeet field");
        }
    }

    @Test
    public void testQuoteHasNumOfRoomField() throws ClassNotFoundException {
        Class quoteClass = getClassByName(".models.Quote");
        Field numOfRoomField = null;
        try {
            numOfRoomField = quoteClass.getDeclaredField("numOfRoom");
        } catch (NoSuchFieldException e) {
            Assertions.fail("Quote class has no numOfRoom field");
        }
    }

    @Test
    public void testQuoteHasNumOfBathroomField() throws ClassNotFoundException {
        Class quoteClass = getClassByName(".models.Quote");
        Field numOfBathroomField = null;
        try {
            numOfBathroomField = quoteClass.getDeclaredField("numOfBathroom");
        } catch (NoSuchFieldException e) {
            Assertions.fail("Quote class has no numOfBathroom field");
        }
    }

    @Test
    public void testQuoteHasCleaningOptionField() throws ClassNotFoundException {
        Class quoteClass = getClassByName(".models.Quote");
        Field cleaningOptionField = null;
        try {
            cleaningOptionField = quoteClass.getDeclaredField("cleaningOption");
        } catch (NoSuchFieldException e) {
            Assertions.fail("Quote class has no cleaningOption field");
        }
    }

    @Test
    public void testQuoteHasTotalChargeField() throws ClassNotFoundException {
        Class quoteClass = getClassByName(".models.Quote");
        Field totalChargeField = null;
        try {
            totalChargeField = quoteClass.getDeclaredField("totalCharge");
        } catch (NoSuchFieldException e) {
            Assertions.fail("Quote class has no totalCharge field");
        }
    }


    @Test
    public void testQuoteHasTotalCostField() throws ClassNotFoundException {
        Class quoteClass = getClassByName(".models.Quote");
        Field totalChargeField = null;
        try {
            totalChargeField = quoteClass.getDeclaredField("totalCost");
        } catch (NoSuchFieldException e) {
            Assertions.fail("Quote class has no totalCost field");
        }
    }

    @Test
    public void testQuoteHasFormattedTotalCostField() throws ClassNotFoundException {
        Class quoteClass = getClassByName(".models.Quote");
        Field formattedTotalCostField = null;
        try {
            formattedTotalCostField = quoteClass.getDeclaredField("formattedTotalCost");
        } catch (NoSuchFieldException e) {
            Assertions.fail("Quote class has no formattedTotalCost field");
        }
    }



}
