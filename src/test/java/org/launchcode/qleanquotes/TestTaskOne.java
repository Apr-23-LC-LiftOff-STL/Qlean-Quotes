package org.launchcode.qleanquotes;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestTaskOne extends AbstractTest{

  //Check application.properties for the correct database connection data
    @Test
    public void testDatabaseConnectionProperties () throws IOException {
        String propsFileContents = getFileContents("src/main/resources/application.properties");

        Pattern urlPattern = Pattern.compile("spring.datasource.url=jdbc:mysql://localhost:3306/teamjupiter");
        Matcher urlMatcher = urlPattern.matcher(propsFileContents);
        boolean urlFound = urlMatcher.find();
        assertTrue(urlFound, "Database connection URL not found or is incorrect");

        Pattern usernamePattern = Pattern.compile("spring.datasource.username=teamjupiter");
        Matcher usernameMatcher= usernamePattern.matcher(propsFileContents);
        boolean usernameFound = usernameMatcher.find();
        assertTrue(usernameFound, "Database username not found or is incorrect");

        Pattern passwordPattern = Pattern.compile("spring.datasource.password=TeamJupiter2023");
        Matcher passwordMatcher= passwordPattern.matcher(propsFileContents);
        boolean passwordFound = passwordMatcher.find();
        assertTrue(passwordFound, "Database password not found or is incorrect");
    }


  //Check build.gradle for the required database dependencies
    @Test
    public void testDatabaseGradleDependencies () throws IOException {
        String gradleFileContents = getFileContents("build.gradle");

        Pattern jpaPattern = Pattern.compile("org.springframework.boot:spring-boot-starter-data-jpa");
        Matcher jpaMatcher = jpaPattern.matcher(gradleFileContents);
        boolean jpaFound = jpaMatcher.find();
        assertTrue(jpaFound, "JPA dependency not found or is incorrect");

        Pattern mysqlPattern = Pattern.compile("mysql:mysql-connector-java");
        Matcher mysqlMatcher = mysqlPattern.matcher(gradleFileContents);
        boolean mysqlFound = mysqlMatcher.find();
        assertTrue(mysqlFound, "MySQL dependency not found or is incorrect");

    }


}
