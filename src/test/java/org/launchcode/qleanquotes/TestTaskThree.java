package org.launchcode.qleanquotes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.lang.annotation.Annotation;
import java.util.Arrays;

import static org.hibernate.validator.internal.util.Contracts.assertTrue;

public class TestTaskThree extends AbstractTest {

/* Verifying that Customer and Quote Repositories exist, have @Repository annotation,
 implement JpaInterface */


    @Test
    public void testCustomerRepositoryExists(){
        try {
            Class customerRepositoryClass = getClassByName(".models.data.CustomerRepository");
        } catch (ClassNotFoundException e){
            Assertions.fail("CustomerRepository does not exist");
        }
    }


    @Test
    public void testCustomerRepositoryHasRepositoryAnnotation () throws ClassNotFoundException {
        Class customerRepositoryClass = getClassByName(".models.data.CustomerRepository");
        Annotation annotation = customerRepositoryClass.getAnnotation(Repository.class);
    }


    @Test
    public void testCustomerRepositoryImplementsJpaInterface () throws ClassNotFoundException {
        Class customerRepositoryClass = getClassByName(".models.data.CustomerRepository");
        Class[] interfaces = customerRepositoryClass.getInterfaces();
        assertTrue(Arrays.asList(interfaces).contains(JpaRepository.class), "CustomerRepository must implement JpaRepository");
    }


    @Test
    public void testQuoteRepositoryExists(){
        try {
            Class quoteRepositoryClass = getClassByName(".models.data.QuoteRepository");
        } catch (ClassNotFoundException e){
            Assertions.fail("QuoteRepository does not exist");
        }
    }


    @Test
    public void testQuoteRepositoryHasRepositoryAnnotation () throws ClassNotFoundException {
        Class quoteRepositoryClass = getClassByName(".models.data.QuoteRepository");
        Annotation annotation = quoteRepositoryClass.getAnnotation(Repository.class);
    }


    @Test
    public void testQuoteRepositoryImplementsJpaInterface () throws ClassNotFoundException {
        Class quoteRepositoryClass = getClassByName(".models.data.QuoteRepository");
        Class[] interfaces = quoteRepositoryClass.getInterfaces();
        assertTrue(Arrays.asList(interfaces).contains(JpaRepository.class), "QuoteRepository must implement JpaRepository");
    }



}
