package fr.thomas.projet;

public class Utilisateur {

    private String nom;
    private String mdp;

    Utilisateur(String nom,String mdp){
        this.nom = nom;
        this.mdp = mdp;
    }

    public String getNom(){
        return nom;
    }

    public void setNom(String string){
        nom = string;
    }

    public String getMdp(){
        return mdp;
    }

    public void setMdp(String string){
        mdp = string;
    }

}
