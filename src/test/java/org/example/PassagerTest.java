package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PassagerTest {

    @Test
    public void testConstructeur(){
        Passager passager = new Passager(1, "Alice Martin", "12 avenue des Champs", "0612345678", 123456789);

        assertEquals(1, passager.identifiant);
        assertEquals("Alice Martin", passager.nom);
        assertEquals("12 avenue des Champs", passager.adresse);
        assertEquals("0612345678", passager.contact);
        assertEquals(123456789, passager.passeport);
    }

    @Test
    public void testReserverVol(){
        Passager passager = new Passager(2, "Bob Dupuis", "34 rue Lafayette", "0687654321", 987654321);

        Reservation reservation = passager.ReserverVol("2025-04-17");

        assertNotNull(reservation);
        assertEquals(1, reservation.numeroReservation);
        assertEquals("2025-04-17", reservation.dateReservation);
        assertEquals("Confirmée", reservation.statut);
        assertEquals(1, passager.reservations.size());
    }

    @Test
    public void testAnnulationVol(){
        Passager passager = new Passager(3, "Chloe Durant", "56 boulevard Haussmann", "0677889900", 112233445);

        passager.ReserverVol("2025-04-18");
        passager.ReserverVol("2025-04-19");

        passager.AnnulerReservation(1);

        Reservation r1 = passager.ObtenirReservation(1);
        Reservation r2 = passager.ObtenirReservation(2);

        assertEquals("Annulée", r1.statut);
        assertEquals("Confirmée", r2.statut);
    }

    @Test
    public void testObtenirReservationInexistante(){
        Passager passager = new Passager(4, "David Henry", "78 rue Nationale", "0655443322", 99887766);

        Reservation res = passager.ObtenirReservation(1);

        assertNull(res);
    }
}