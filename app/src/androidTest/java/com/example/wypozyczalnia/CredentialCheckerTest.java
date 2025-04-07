package com.example.wypozyczalnia;

import org.junit.Test;
import static org.junit.Assert.*;

public class CredentialCheckerTest {

    @Test
    public void testValidCredentials() {
        String fileData = "Jan;Kowalski;jan@wp.pl;1234\nAnna;Nowak;anna@o2.pl;haslo";
        assertTrue(CredentialChecker.checkCredentials(fileData, "jan@wp.pl", "1234"));
    }

    @Test
    public void testInvalidEmail() {
        String fileData = "Jan;Kowalski;jan@wp.pl;1234";
        assertFalse(CredentialChecker.checkCredentials(fileData, "nie@ma.pl", "1234"));
    }

    @Test
    public void testInvalidPassword() {
        String fileData = "Jan;Kowalski;jan@wp.pl;1234";
        assertFalse(CredentialChecker.checkCredentials(fileData, "jan@wp.pl", "zlehaslo"));
    }

    @Test
    public void testMalformedLine() {
        String fileData = "Jan;Kowalski;jan@wp.pl\nAnna;Nowak;anna@o2.pl;haslo";
        assertTrue(CredentialChecker.checkCredentials(fileData, "anna@o2.pl", "haslo"));
    }

    @Test
    public void testEmptyFile() {
        String fileData = "";
        assertFalse(CredentialChecker.checkCredentials(fileData, "jan@wp.pl", "1234"));
    }
}
