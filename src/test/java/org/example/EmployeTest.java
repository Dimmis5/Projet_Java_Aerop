package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeTest {

    @Test
    public void testConstructeur(){
        Employe emp = new Employe(2, "Marie Dubois", "456 rue de Lyon", "0605060708", 1001, "2023-09-15");

        assertEquals(2, emp.identifiant);
        assertEquals("Marie Dubois", emp.nom);
        assertEquals("456 rue de Lyon", emp.adresse);
        assertEquals("0605060708", emp.contact);
        assertEquals(1001, emp.NumeroEmploye);
        assertEquals("2023-09-15", emp.DateEmbauche);
    }

    @Test
    public void testObtenirRole(){
        Employe emp = new Employe(3, "Paul Martin", "789 rue Victor Hugo", "0611223344", 1002, "2022-05-10");

        assertEquals("Personnel Cabine", emp.ObtenirRole());
    }
}