package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonnelCabineTest {

    @Test
    public void testConstructeur(){
        PersonnelCabine pc = new PersonnelCabine(10, "Sophie", "Paris", "0611223344", 3001, "2023-03-01", "Chef de cabine");
        assertEquals(10, pc.identifiant);
        assertEquals("Sophie", pc.nom);
        assertEquals("Paris", pc.adresse);
        assertEquals("0611223344", pc.contact);
        assertEquals(3001, pc.NumeroEmploye);
        assertEquals("2023-03-01", pc.DateEmbauche);
        assertEquals("Chef de cabine", pc.qualification);
    }

}