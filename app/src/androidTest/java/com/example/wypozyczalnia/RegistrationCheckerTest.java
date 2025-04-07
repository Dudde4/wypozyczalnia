package com.example.wypozyczalnia;

import org.junit.Test;
import static org.junit.Assert.*;

import com.example.wypozyczalnia.testy.RegistrationChecker;

public class RegistrationCheckerTest {

    @Test
    public void testEmailExists_True() {
        String data = "Jan;Kowalski;jan@wp.pl;1234\nAnna;Nowak;anna@o2.pl;haslo";
        assertTrue(RegistrationChecker.emailExists(data, "jan@wp.pl"));
    }

    @Test
    public void testEmailExists_False() {
        String data = "Jan;Kowalski;jan@wp.pl;1234\nAnna;Nowak;anna@o2.pl;haslo";
        assertFalse(RegistrationChecker.emailExists(data, "nieistnieje@wp.pl"));
    }

    @Test
    public void testEmailExists_EmptyFile() {
        String data = "";
        assertFalse(RegistrationChecker.emailExists(data, "jan@wp.pl"));
    }

    @Test
    public void testFormatUserData() {
        String result = RegistrationChecker.formatUserData("Ala", "Nowak", "ala@wp.pl", "abc123");
        assertEquals("Ala;Nowak;ala@wp.pl;abc123\n", result);
    }
}