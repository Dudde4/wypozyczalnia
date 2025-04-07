package com.example.wypozyczalnia.testy;

public class CredentialChecker {

    public static boolean checkCredentials(String fileContents, String email, String haslo) {
        String[] lines = fileContents.split("\n");

        for (String line : lines) {
            String[] parts = line.split(";");
            if (parts.length == 4) {
                String savedEmail = parts[2].trim();
                String savedHaslo = parts[3].trim();

                if (savedEmail.equals(email) && savedHaslo.equals(haslo)) {
                    return true;
                }
            }
        }

        return false;
    }
}
