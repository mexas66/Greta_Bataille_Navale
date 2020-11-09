package fr.greta.java;

import java.util.List;
import java.util.Scanner;

public class UI {

    Scanner scanner = new Scanner(System.in);


    public void messageDebutPlacerBateau(Joueur joueur) {
        System.out.println("A "+ joueur.pseudo + " de placer ses bateaux");
    }




    public void messagePlacerBateau(BateauType type) {
        System.out.println("On va placer le " + type.name().toLowerCase());
    }




    public void messageRecupererPositionAvant() {
        System.out.println("Merci de saisir la position avant du bateau");
    }

    public Position recupererPosition() {

        int x = scanner.nextInt();
        int y = scanner.nextInt();
        return new Position(x,y);
    }




    public Position recupererPositionArriere(List<Position> positionArriereDispo) {
        int choix;
        do{
            choix = scanner.nextInt();
        }while (choix < 0 || choix >= positionArriereDispo.size());
        return positionArriereDispo.get(choix);
    }




    public void messageRecupererPositionArriere(List<Position> positionArriereDispo) {
        System.out.print("Les positions arrière disponibles sont: ");
        for (Position position: positionArriereDispo){
            System.out.print(positionArriereDispo.indexOf(position) + "["+position.getX()+","+position.getY()+"] ");
        }
        System.out.println();
        System.out.println("Merci de saisir la position arrière du bateau");
    }




    public void messageErreurPlacementBateau(String message) {
        System.err.println("Erreur dans la saisie des coordonnées");
    }

    public void demandeAjoutBateau(BateauType type) {
        System.out.println("Voulez vous jouer avec le " + type.name().toLowerCase() + "? (O pour oui/N pour non)");
    }




    public boolean saisieAjoutBateau() throws RegleException {
        String saisie;
        do{
            saisie = scanner.next();
            if(!saisie.equals("O") && !saisie.equals("N")){
                throw new RegleException();
            }
        }while(!saisie.equals("O") && !saisie.equals("N"));

        if(saisie.equals("O")){
            return true;
        }
        return false;
    }




    public void demandeTir(Joueur joueurCourant) {
        System.out.println("A "+ joueurCourant.pseudo+ " de tirer");
    }




    public void touche() {
        System.out.print("TOUCHE ");
    }




    public void manque() {
        System.out.println("Manqué...");
    }




    public void victoire(Joueur joueurCourant) {
        System.out.println(joueurCourant.pseudo + " a gagné!");
    }




    public void bienvenue() {
        System.out.println("Bienvenue dans cette nouvelle partie de bataille navale!!");
    }




    public void demandePseudoJ1() {
        System.out.println("Comment s'appelle le joueur 1?");
    }




    public void demandePseudoJ2() {
        System.out.println("Comment s'appelle le joueur 2?");
    }




    public String saisiePseudo() {
        return scanner.next();
    }




    public void clearConsole() {
        System.out.flush();
    }




    public void coule() {
        System.out.print("COULE!!");
    }
}
