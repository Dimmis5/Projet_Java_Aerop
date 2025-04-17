package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonneTest {

    @Test
    public void testConstruceur(){
        Personne personne = new Personne(1, "Jean Dupont", "123 rue de Paris", "0601020304");

        assertEquals(1, personne.identifiant);
        assertEquals("Jean Dupont", personne.nom);
        assertEquals("123 rue de Paris", personne.adresse);
        assertEquals("0601020304", personne.contact);
    }
}