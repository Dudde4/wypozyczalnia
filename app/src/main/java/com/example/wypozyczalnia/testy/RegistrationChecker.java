package com.example.wypozyczalnia.testy;

public class RegistrationChecker {

    public static boolean emailExists(String fileContents, String email) {
        String[] lines = fileContents.split("\n");
        for (String line : lines) {
            String[] parts = line.split(";");
            if (parts.length >= 3 && parts[2].trim().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public static String formatUserData(String imie, String nazwisko, String email, String haslo) {
        return imie + ";" + nazwisko + ";" + email + ";" + haslo + "\n";
    }
}
