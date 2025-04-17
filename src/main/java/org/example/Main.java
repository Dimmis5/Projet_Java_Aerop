package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
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

    public void ObtenirInfos() {
        System.out.println("Personne : ");
        System.out.println("Identifiant : " + identifiant);
        System.out.println("Nom : " + nom);
        System.out.println("Adresse : " + adresse);
        System.out.println("Contact : " + contact);
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

    @Override
    public void ObtenirInfos() {
        super.ObtenirInfos();
        System.out.println("Numero d'employe : " + NumeroEmploye);
        System.out.println("Date d'embauche : " +  DateEmbauche);
    }

    public String ObtenirRole() {
        if (this instanceof Pilote) {
            return "Pilote";
        } else {
            return "Personnel Cabine";
        }
    }
}

class Passager extends Personne {
    protected int passeport;
    protected List<Reservation> reservations;

    public Passager(int identifiant, String nom, String adresse, String contact, int passeport) {
        super(identifiant, nom, adresse, contact);
        this.passeport = passeport;
        this.reservations = new ArrayList<>();
    }

    @Override
    public void ObtenirInfos() {
        super.ObtenirInfos();
        System.out.println("Passeport : " + passeport);
    }

    public Reservation ReserverVol(String dateReservation) {
        int numeroreservation = reservations.size() + 1;
        Reservation reservation = new Reservation(numeroreservation, dateReservation, "Confirmée");
        reservations.add(reservation);
        return reservation;
    }

    public void AnnulerReservation(int numeroreservation) {
        for (Reservation reservation : reservations) {
            if (reservation.numeroReservation == numeroreservation) {
                reservation.statut = "Annulée";
            }
        }
    }

    public Reservation ObtenirReservation(int numeroreservation) {
        for (Reservation reservation : reservations) {
            if (reservation.numeroReservation == numeroreservation) {
                return reservation;
            }
        }
        return null;
    }
}

class Pilote extends Employe {
    protected String Licence;
    protected int heuresDeVol;
    protected List<Vol> vol;

    public Pilote(int identifiant, String nom, String adresse, String contact, int NumeroEmploye, String DateEmbauche, String Licence, int heuresDeVol) {
        super(identifiant, nom, adresse, contact, NumeroEmploye, DateEmbauche);
        this.Licence = Licence;
        this.heuresDeVol = heuresDeVol;
        this.vol = new ArrayList<>();
    }

    @Override
    public void ObtenirInfos() {
        super.ObtenirInfos();
        System.out.println("Licence : " + Licence);
        System.out.println("Heures de vol : " +  heuresDeVol);
    }

    public void affecterVol(Vol vol, List<PersonnelCabine> equipeCabine) {
        this.vol.add(vol);
        vol.setPilote(this);
        vol.setEquipeCabine(equipeCabine);
        System.out.println("Vol : " + vol.numeroVol);
        System.out.println("Pilote : " + this.nom);
        System.out.print("Equipe de cabine : ");
        for (int i = 0; i < equipeCabine.size(); i++) {
            if (i <= 0) {
                System.out.println(equipeCabine.get(i).nom);
            }
        }
        System.out.println();
    }

    public Vol ObtenirVol(int numeroVol) {
        for (Vol vol : vol) {
            if (vol.numeroVol == numeroVol) {
                return vol;
            }
        }
        return null;
    }
}

class PersonnelCabine extends Employe {
    protected String qualification;
    protected List<Vol> vol;

    public PersonnelCabine(int identifiant, String nom, String adresse, String contact, int NumeroEmploye, String DateEmbauche, String qualification) {
        super(identifiant, nom, adresse, contact, NumeroEmploye, DateEmbauche);
        this.qualification = qualification;
    }

    @Override
    public void ObtenirInfos() {
        super.ObtenirInfos();
        System.out.println("Qualification : " + qualification);
    }

    public void affecterVol(Vol vol) {
        this.vol.add(vol);
        vol.setPilote(pilote);
        List<PersonnelCabine> equipeCabine = new ArrayList<>();
        equipeCabine.add(this);
        vol.setEquipeCabine(equipeCabine);
        System.out.println("Vol : " + vol.numeroVol);
        System.out.println("Pilote : " + pilote);
        System.out.println("Equipe de cabine : " + this);
    }

    public Vol ObtenirVol(int numeroVol) {
        for (Vol vol : vol) {
            if (vol.numeroVol == numeroVol) {
                return vol;
            }
        }
        return null;
    }
}

class Avion{
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

    public void ajouterVol(Vol vol) {
        volsAffectes.add(vol);
    }

    public List<Vol> getVolsAffectes() {
        return volsAffectes;
    }
}

class Reservation{
    protected int numeroReservation;
    protected String dateReservation;
    protected String statut;
    protected Passager passager;
    protected List<Reservation> reservations;

    public Reservation(int numeroReservation, String dateReservation, String statut){
        this.numeroReservation = numeroReservation;
        this.dateReservation = dateReservation;
        this.statut = statut;
        this.reservations = new ArrayList<>();

    }

    public void ConfirmerReservation(int numeroreservation) {
        for (Reservation reservation : reservations) {
            if (reservation.numeroReservation == numeroreservation) {
                reservation.statut = "Confirmée";
            }
        }
    }

    public void AnnulerReservation(int numeroreservation) {
        for (Reservation reservation : reservations) {
            if (reservation.numeroReservation == numeroreservation) {
                reservation.statut = "Annulée";
            }
        }
    }

    public void ModifierReservation(int numeroreservation, String dateReservation) {
        for (Reservation reservation : reservations) {
            if (reservation.numeroReservation == numeroreservation) {
                reservation.dateReservation = dateReservation;
            }
        }
    }
}

class Vol{
    protected int numeroVol;
    protected String origine;
    protected String destination;
    protected String dateHeureDepart;
    protected String dateHeureArrivee;
    protected boolean etat;
    protected String aeroportArrivee;
    protected String aeroportDepart;
    protected List<Reservation> reservations;
    private Avion avion;
    private Pilote pilote;
    private List<PersonnelCabine> equipeCabine;


    public Vol(int numeroVol, String origine, String destination, String dateHeureDepart, String dateHeureArrivee, boolean etat, String aeroportDepart, String aeroportArrivee){
        this.numeroVol = numeroVol;
        this.origine = origine;
        this.destination = destination;
        this.dateHeureDepart= dateHeureDepart;
        this.dateHeureArrivee = dateHeureArrivee;
        this.etat = etat;
        this.aeroportArrivee = aeroportArrivee;
        this.aeroportDepart = aeroportDepart;
        this.reservations = new ArrayList<>();
    }

    public Pilote getPilote(Pilote pilote) {
        return pilote;
    }

    public List<PersonnelCabine> getEquipeCabine() {
        return new ArrayList<>(equipeCabine);
    }

    public void setPilote(Pilote pilote) {
        this.pilote = pilote;
    }

    public void setEquipeCabine(List<PersonnelCabine> equipeCabine) {
        this.equipeCabine = equipeCabine;
    }

    public static void planifierVol(List<Avion> avions, List<Vol> vols){
        for (Vol vol : vols){
            for (Avion avion : avions){
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

    public static boolean modifierVol(List<Avion> avions, int numeroVol, String nouvelleOrigine, String nouvelleDestination, String nouvelleDateDepart, String nouvelleDateArrivee, boolean nouvelEtat) {
        Vol vol = trouverVolParNumero(avions, numeroVol);
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
            return Avion.affecterVol(avions, vol);
        }
        return true;
    }

    public static Vol trouverVolParNumero(List<Avion> avions, int numeroVol){
        for (Avion avion : avions){
            for(Vol vol : avion.volsAffectes){
                if (vol.numeroVol == numeroVol){
                    return vol;
                }
            }
        }
        return null;
    }

    public void ListingPassager() {
        if (reservations.isEmpty()) {
            return;
        }
        for (Reservation reservation : reservations) {
            if (reservation.statut == "Confirmée") {
                System.out.println(reservation.numeroReservation);
            }
        }
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