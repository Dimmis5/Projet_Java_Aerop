package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

    }
}

class Avion{
    protected String immatriculation;
    protected String model;
    protected int capacite;

    public Avion(String immatriculation, String model, int capacite){
        this.immatriculation = immatriculation;
        this.model = model;
        this.capacite = capacite;
    }
}

class Reservation{
    protected int numeroReservation;
    protected String dateReservation;
    protected String statut;

    public Reservation(int numeroReservation, String dateReservation, String statut){
        this.numeroReservation = numeroReservation;
        this.dateReservation = dateReservation;
        this.statut = statut;
    }
}

class Vol{
    protected int numeroVol;
    protected String origine;
    protected String destination;
    protected String dateHeureDepart;
    protected String dateHeureArrivee;
    protected boolean etat;

    public Vol(int numeroVol, String origine, String destination, String dateHeureDepart, String dateHeureArrivee, boolean etat){
        this.numeroVol = numeroVol;
        this.origine = origine;
        this.destination = destination;
        this.dateHeureDepart= dateHeureDepart;
        this.dateHeureArrivee = dateHeureArrivee;
        this.etat = etat;
    }
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