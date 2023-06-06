package org.launchcode.qleanquotes;

import jakarta.validation.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.launchcode.qleanquotes.models.dto.CreateQuoteFormDTO;
import org.launchcode.qleanquotes.models.dto.LoginFormDTO;
import org.launchcode.qleanquotes.models.dto.ProfileFormDTO;
import org.launchcode.qleanquotes.models.dto.RegisterFormDTO;
import org.launchcode.qleanquotes.models.enums.CleaningOption;

import java.util.Set;

public class TestTaskFour extends AbstractTest {

 /* verifying the getter and setter methods of the DTO classes. LoginFormDTO, CreateQuoteFormDTO, RegisterFormDTO*/


    @Test
    void getEmailValidEmailReturnsEmail() {
        String email = "test@example.com";
        LoginFormDTO loginFormDTO = new LoginFormDTO();
        loginFormDTO.setEmail(email);

        String actualEmail = loginFormDTO.getEmail();

        Assertions.assertEquals(email, actualEmail);
    }

    @Test
    void getPasswordValidPasswordReturnsPassword() {
        String password = "password123";
        LoginFormDTO loginFormDTO = new LoginFormDTO();
        loginFormDTO.setPassword(password);

        String actualPassword = loginFormDTO.getPassword();

        Assertions.assertEquals(password, actualPassword);
    }


    @Test
    void getPhoneNumberReturnsValidPhoneNumber() {
        String phoneNumber = "1234567890";
        ProfileFormDTO profileFormDTO = new ProfileFormDTO();
        profileFormDTO.setPhoneNumber(phoneNumber);

        String actualPhoneNumber = profileFormDTO.getPhoneNumber();

        Assertions.assertEquals(phoneNumber, actualPhoneNumber);
    }


    @Test
    void setPhoneNumberSetsValidPhoneNumber() {
        String phoneNumber = "1234567890";
        ProfileFormDTO profileFormDTO = new ProfileFormDTO();
        profileFormDTO.setPhoneNumber(phoneNumber);

        Assertions.assertEquals(phoneNumber, profileFormDTO.getPhoneNumber());
    }



    @Test
    void getSquareFeetReturnsValidSquareFeet() {
        Integer squareFeet = 100;
        CreateQuoteFormDTO createQuoteFormDTO = new CreateQuoteFormDTO();
        createQuoteFormDTO.setSquareFeet(squareFeet);

        Integer actualSquareFeet = createQuoteFormDTO.getSquareFeet();

        Assertions.assertEquals(squareFeet, actualSquareFeet);
    }

    @Test
    void setSquareFeetSetsValidSquareFeet() {
        Integer squareFeet = 100;
        CreateQuoteFormDTO createQuoteFormDTO = new CreateQuoteFormDTO();
        createQuoteFormDTO.setSquareFeet(squareFeet);

        Assertions.assertEquals(squareFeet, createQuoteFormDTO.getSquareFeet());
    }

    @Test
    void setSquareFeetSquareFeetTooLowThrowsException() {
        Integer squareFeet = 50;
        CreateQuoteFormDTO createQuoteFormDTO = new CreateQuoteFormDTO();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<CreateQuoteFormDTO>> violations = validator.validate(createQuoteFormDTO);

        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            createQuoteFormDTO.setSquareFeet(squareFeet);
            if (!violations.isEmpty()) {
                throw new ConstraintViolationException(violations);
            }
        });
    }


    @Test
    void getNumOfRoomReturnsValidNumOfRoom() {
        Integer numOfRoom = 3;
        CreateQuoteFormDTO createQuoteFormDTO = new CreateQuoteFormDTO();
        createQuoteFormDTO.setNumOfRoom(numOfRoom);

        Integer actualNumOfRoom = createQuoteFormDTO.getNumOfRoom();

        Assertions.assertEquals(numOfRoom, actualNumOfRoom);
    }

    @Test
    void setNumOfRoomSetsValidNumOfRoom() {
        Integer numOfRoom = 3;
        CreateQuoteFormDTO createQuoteFormDTO = new CreateQuoteFormDTO();
        createQuoteFormDTO.setNumOfRoom(numOfRoom);

        Assertions.assertEquals(numOfRoom, createQuoteFormDTO.getNumOfRoom());
    }

    @Test
    void setNumOfRoomNumOfRoomTooLowThrowsException() {
        Integer numOfRoom = 0;
        CreateQuoteFormDTO createQuoteFormDTO = new CreateQuoteFormDTO();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<CreateQuoteFormDTO>> violations = validator.validate(createQuoteFormDTO);

        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            createQuoteFormDTO.setNumOfRoom(numOfRoom);
            if (!violations.isEmpty()) {
                throw new ConstraintViolationException(violations);
            }
        });
    }


    @Test
    void getNumOfBathroomReturnsValidNumOfBathroom() {
        Integer numOfBathroom = 2;
        CreateQuoteFormDTO createQuoteFormDTO = new CreateQuoteFormDTO();
        createQuoteFormDTO.setNumOfBathroom(numOfBathroom);

        Integer actualNumOfBathroom = createQuoteFormDTO.getNumOfBathroom();

        Assertions.assertEquals(numOfBathroom, actualNumOfBathroom);
    }

    @Test
    void setNumOfBathroomSetsValidNumOfBathroom() {
        Integer numOfBathroom = 2;
        CreateQuoteFormDTO createQuoteFormDTO = new CreateQuoteFormDTO();
        createQuoteFormDTO.setNumOfBathroom(numOfBathroom);

        Assertions.assertEquals(numOfBathroom, createQuoteFormDTO.getNumOfBathroom());
    }

    @Test
    void getCleaningOptionReturnsValidCleaningOptionAverage() {
        CleaningOption cleaningOption = CleaningOption.AVERAGE;
        CreateQuoteFormDTO createQuoteFormDTO = new CreateQuoteFormDTO();
        createQuoteFormDTO.setCleaningOption(cleaningOption);

        CleaningOption actualCleaningOption = createQuoteFormDTO.getCleaningOption();

        Assertions.assertEquals(cleaningOption, actualCleaningOption);
    }

    @Test
    void setCleaningOptionSetsValidCleaningOptionAverage() {
        CleaningOption cleaningOption = CleaningOption.AVERAGE;
        CreateQuoteFormDTO createQuoteFormDTO = new CreateQuoteFormDTO();
        createQuoteFormDTO.setCleaningOption(cleaningOption);

        Assertions.assertEquals(cleaningOption, createQuoteFormDTO.getCleaningOption());
    }



    @Test
    void setCleaningOptionSetsValidCleaningOptionDeep() {
        CleaningOption cleaningOption = CleaningOption.DEEP;
        CreateQuoteFormDTO createQuoteFormDTO = new CreateQuoteFormDTO();
        createQuoteFormDTO.setCleaningOption(cleaningOption);

        Assertions.assertEquals(cleaningOption, createQuoteFormDTO.getCleaningOption());
    }

    @Test
    void getCleaningOptionReturnsValidCleaningOptionDeep() {
        CleaningOption cleaningOption = CleaningOption.DEEP;
        CreateQuoteFormDTO createQuoteFormDTO = new CreateQuoteFormDTO();
        createQuoteFormDTO.setCleaningOption(cleaningOption);

        CleaningOption actualCleaningOption = createQuoteFormDTO.getCleaningOption();

        Assertions.assertEquals(cleaningOption, actualCleaningOption);
    }

    @Test
    void setCleaningOptionNullThrowsException() {
        CleaningOption cleaningOption = null;
        CreateQuoteFormDTO createQuoteFormDTO = new CreateQuoteFormDTO();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<CreateQuoteFormDTO>> violations = validator.validate(createQuoteFormDTO);

        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            createQuoteFormDTO.setCleaningOption(cleaningOption);
            if (!violations.isEmpty()) {
                throw new ConstraintViolationException(violations);
            }
        });
    }

    @Test
    void getTotalChargeReturnsValidTotalCharge() {
        Long totalCharge = 100L;
        CreateQuoteFormDTO createQuoteFormDTO = new CreateQuoteFormDTO();
        createQuoteFormDTO.setTotalCharge(totalCharge);

        Long actualTotalCharge = createQuoteFormDTO.getTotalCharge();

        Assertions.assertEquals(totalCharge, actualTotalCharge);
    }

    @Test
    void setTotalChargeSetsValidTotalCharge() {
        Long totalCharge = 100L;
        CreateQuoteFormDTO createQuoteFormDTO = new CreateQuoteFormDTO();
        createQuoteFormDTO.setTotalCharge(totalCharge);

        Assertions.assertEquals(totalCharge, createQuoteFormDTO.getTotalCharge());
    }

    @Test
    void getFormattedTotalCostReturnsValidFormattedTotalCost() {
        String formattedTotalCost = "$100.00";
        CreateQuoteFormDTO createQuoteFormDTO = new CreateQuoteFormDTO();
        createQuoteFormDTO.setFormattedTotalCost(formattedTotalCost);

        String actualFormattedTotalCost = createQuoteFormDTO.getFormattedTotalCost();

        Assertions.assertEquals(formattedTotalCost, actualFormattedTotalCost);
    }

    @Test
    void setFormattedTotalCostSetsValidFormattedTotalCost() {
        String formattedTotalCost = "$100.00";
        CreateQuoteFormDTO createQuoteFormDTO = new CreateQuoteFormDTO();
        createQuoteFormDTO.setFormattedTotalCost(formattedTotalCost);

        Assertions.assertEquals(formattedTotalCost, createQuoteFormDTO.getFormattedTotalCost());
    }


    @Test
    void getNameReturnsValidName() {
        String name = "John";
        RegisterFormDTO registerFormDTO = new RegisterFormDTO();
        registerFormDTO.setName(name);

        String actualName = registerFormDTO.getName();

        Assertions.assertEquals(name, actualName);
    }

    @Test
    void setNameSetsValidName() {
        String name = "John";
        RegisterFormDTO registerFormDTO = new RegisterFormDTO();
        registerFormDTO.setName(name);

        Assertions.assertEquals(name, registerFormDTO.getName());
    }

    @Test
    void setNameNameTooShortThrowsException() {
        String name = "Jo";
        RegisterFormDTO registerFormDTO = new RegisterFormDTO();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<RegisterFormDTO>> violations = validator.validate(registerFormDTO);

        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            registerFormDTO.setName(name);
            if (!violations.isEmpty()) {
                throw new ConstraintViolationException(violations);
            }
        });
    }

    @Test
    void getLastNameReturnsValidLastName() {
        String lastName = "Doe";
        RegisterFormDTO registerFormDTO = new RegisterFormDTO();
        registerFormDTO.setLastName(lastName);

        String actualLastName = registerFormDTO.getLastName();

        Assertions.assertEquals(lastName, actualLastName);
    }

    @Test
    void setLastNameSetsValidLastName() {
        String lastName = "Doe";
        RegisterFormDTO registerFormDTO = new RegisterFormDTO();
        registerFormDTO.setLastName(lastName);

        Assertions.assertEquals(lastName, registerFormDTO.getLastName());
    }

    @Test
    void setLastNameLastNameTooShortThrowsException() {
        String lastName = "D";
        RegisterFormDTO registerFormDTO = new RegisterFormDTO();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<RegisterFormDTO>> violations = validator.validate(registerFormDTO);

        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            registerFormDTO.setLastName(lastName);
            if (!violations.isEmpty()) {
                throw new ConstraintViolationException(violations);
            }
        });
    }

    @Test
    void getEmailReturnsValidEmail() {
        String email = "test@example.com";
        RegisterFormDTO registerFormDTO = new RegisterFormDTO();
        registerFormDTO.setEmail(email);

        String actualEmail = registerFormDTO.getEmail();

        Assertions.assertEquals(email, actualEmail);
    }

    @Test
    void setEmailSetsValidEmail() {
        String email = "test@example.com";
        RegisterFormDTO registerFormDTO = new RegisterFormDTO();
        registerFormDTO.setEmail(email);

        Assertions.assertEquals(email, registerFormDTO.getEmail());
    }






}


