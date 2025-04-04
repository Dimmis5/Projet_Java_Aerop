package org.example;

import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

    }
}

class Avion {
    protected String immatriculation;
    protected String model;
    protected int capacite;
    protected List<Vol> volsAffectes;

    public Avion(String immatriculation, String model, int capacite) {
        this.immatriculation = immatriculation;
        this.model = model;
        this.capacite = capacite;
        this.volsAffectes = new ArrayList<>();
    }

    public boolean affecterVol(Vol vol) {
        if (this.verifierDisponibilite(vol.dateHeureDepart, vol.dateHeureArrivee)) {
            this.volsAffectes.add(vol);
            return true;
        }
        return false;
    }

    public static boolean affecterVol(List<Avion> flotte, Vol vol) {
        for (Avion avion : flotte) {
            if (avion.affecterVol(vol)) {
                return true;
            }
        }
        return false;
    }


    public boolean verifierDisponibilite(String nouvelleDateDepart, String nouvelleDateArrivee) {
        for (Vol vol : volsAffectes) {
            if (!(nouvelleDateArrivee.compareTo(vol.dateHeureDepart) < 0 || nouvelleDateDepart.compareTo(vol.dateHeureArrivee) > 0)) {
                return false;
            }
        }
        return true;
    }
    public void ajouterVol(Vol vol){
        volsAffectes.add(vol);
    }
}

class Reservation{
    protected int numeroReservation;
    protected String dateReservation;
    protected String statut;
    protected Passager passager;


    public Reservation(int numeroReservation, String dateReservation, String statut){
        this.numeroReservation = numeroReservation;
        this.dateReservation = dateReservation;
        this.statut = statut;
    }

    public static boolean annulerReservation(List<Reservation> reservations, int numeroReservation){
        for(int i = 0; i < reservations.size(); i++){
            Reservation resa = reservations.get(i);
            if (resa.numeroReservation == numeroReservation){
                resa.statut = "ANNULEE";
                reservations.remove(i);
                return true;
            }
        }
        return false;
    }
}

class Vol{
    protected int numeroVol;
    protected String origine;
    protected String destination;
    protected String dateHeureDepart;
    protected String dateHeureArrivee;
    protected boolean etat;
    private Avion avion;
    protected String aeroportArriver;
    protected String aeroportDepart;

    public Vol(int numeroVol, String origine, String destination, String dateHeureDepart, String dateHeureArrivee, boolean etat, String aeroportArriver, String aeroportDepart){
        this.numeroVol = numeroVol;
        this.origine = origine;
        this.destination = destination;
        this.dateHeureDepart= dateHeureDepart;
        this.dateHeureArrivee = dateHeureArrivee;
        this.etat = etat;
        this.aeroportArriver = aeroportArriver;
        this.aeroportDepart = aeroportDepart;
    }

    public static void planifierVol(List<Avion> flotte, List<Vol> vols){
        for (Vol vol : vols){
            for (Avion avion : flotte){
                if (avion.verifierDisponibilite(vol.dateHeureDepart, vol.dateHeureArrivee)){
                    avion.ajouterVol(vol);
                    vol.avion = avion;
                    break;
                }
            }
        }
    }

    public static boolean annulerVol(List<Avion> flotte, int numeroVol){
        for (Avion avion : flotte){
            for (int i = 0; i < avion.volsAffectes.size(); i++){
                Vol vol = avion.volsAffectes.get(i);
                if(vol.numeroVol == numeroVol){
                    avion.volsAffectes.remove(i);
                    vol.etat = false;
                    vol.avion = null;
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean modifierVol(List<Avion> flotte, int numeroVol, String nouvelleOrigine, String nouvelleDestination, String nouvelleDateDepart, String nouvelleDateArrivee, boolean nouvelEtat) {

        Vol vol = trouverVolParNumero(flotte, numeroVol);
        if (vol == null) {
            return false;
        }

        Avion ancienAvion = vol.avion;

        if (ancienAvion != null) {
            ancienAvion.volsAffectes.remove(vol);
        }

        vol.origine = nouvelleOrigine;
        vol.destination = nouvelleDestination;
        vol.dateHeureDepart = nouvelleDateDepart;
        vol.dateHeureArrivee = nouvelleDateArrivee;
        vol.etat = nouvelEtat;
        vol.avion = null;

        if (nouvelEtat) {
            return Avion.affecterVol(flotte, vol);
        }

        return true;
    }

    public static Vol trouverVolParNumero(List<Avion> flotte, int numeroVol){
        for (Avion avion : flotte){
            for(Vol vol : avion.volsAffectes){
                if (vol.numeroVol == numeroVol){
                    return vol;
                }
            }
        }
        return null;
    }

    public
}

class Aeroport{
    protected String nom;
    protected String ville;
    protected String description;

    public Aeroport(String nom, String ville, String description){
        this.nom = nom;
        this.ville = ville;
        this.description = description;
    }
    public boolean affecterVol(Vol vol) {
        if (!vol.origine.equals(this.nom)) {
            return false;
        }
        return true;
    }

    public static boolean affecterVol(List<Aeroport> aeroports, Vol vol) {
        for (Aeroport aeroport : aeroports) {
            if (aeroport.nom.equals(vol.origine)) {
                return aeroport.affecterVol(vol);
            }
        }
        return false;
    }
}

class Personne {
    protected int identifiant;
    protected String nom;
    protected String adresse;
    protected String contact;

    public Personne(int identifiant, String nom, String adresse, String contact) {
        this.identifiant = identifiant;
        this.nom = nom;
        this.adresse = adresse;
        this.contact = contact;
    }
}

class Employe extends Personne {
    protected int NumeroEmploye;
    protected String DateEmbauche;

    public Employe(int identifiant, String nom, String adresse, String contact, int NumeroEmploye, String DateEmbauche) {
        super(identifiant, nom, adresse, contact);
        this.NumeroEmploye = NumeroEmploye;
        this.DateEmbauche = DateEmbauche;
    }
}

class Passager extends Personne {
    protected int passeport;

    public Passager(int identifiant, String nom, String adresse, String contact, int passeport) {
        super(identifiant, nom, adresse, contact);
        this.passeport = passeport;
    }
}

class Pilote extends Employe {
    protected String Licence;
    protected int heuresDeVol;

    public Pilote(int identifiant, String nom, String adresse, String contact, int NumeroEmploye, String DateEmbauche, String Licence, int heuresDeVol) {
        super(identifiant, nom, adresse, contact, NumeroEmploye, DateEmbauche);
        this.Licence = Licence;
        this.heuresDeVol = heuresDeVol;
    }
}

class PersonnelCabine extends Employe {
    String qualification;

    public PersonnelCabine(int identifiant, String nom, String adresse, String contact, int NumeroEmploye, String DateEmbauche, String qualification) {
        super(identifiant, nom, adresse, contact, NumeroEmploye, DateEmbauche);
        this.qualification = qualification;
    }
}