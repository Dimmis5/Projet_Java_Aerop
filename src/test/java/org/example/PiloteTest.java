package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PiloteTest {

    @Test

    public void testConstructeur(){
        Pilote pilote = new Pilote(1, "Jean Volant", "10 rue du ciel", "0600000001", 2001, "2020-01-01", "PIL123", 1500);

        assertEquals(1, pilote.identifiant);
        assertEquals("Jean Volant", pilote.nom);
        assertEquals("10 rue du ciel", pilote.adresse);
        assertEquals("0600000001", pilote.contact);
        assertEquals(2001, pilote.NumeroEmploye);
        assertEquals("2020-01-01", pilote.DateEmbauche);
        assertEquals("PIL123", pilote.Licence);
        assertEquals(1500, pilote.heuresDeVol);
    }

}