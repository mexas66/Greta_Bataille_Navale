package fr.greta.java;

import java.util.List;

public class Runner {



    
    public static void main(String[] args) {
        Partie partie = new Partie();
        UI ui  = new UI();

        ui.bienvenue();

        Joueur joueur1 = new Joueur();
        ui.demandePseudoJ1();
        joueur1.pseudo = ui.saisiePseudo();
        partie.joueur1 = joueur1;

        Joueur joueur2 = new Joueur();
        ui.demandePseudoJ2();
        joueur2.pseudo = ui.saisiePseudo();
        partie.joueur2 = joueur2;

        List<BateauType> bateaux = partie.bateauAPlacer();

        ui.messageDebutPlacerBateau(joueur1);
        saisieJoueur(bateaux, ui, partie , joueur1);


        ui.messageDebutPlacerBateau(joueur2);
        saisieJoueur(bateaux,ui, partie , joueur2);

        Joueur joueurCourant = joueur1;

        while(!partie.estTermine(joueurCourant)){

            ui.demandeTir(joueurCourant);
            Position positionTir = ui.recupererPosition();

            try {
                if (partie.tirer(joueurCourant, positionTir)) {
                    ui.touche();
                    if(partie.checkBateauEstCoule(joueurCourant,positionTir)){
                        ui.coule();
                    }
                    System.out.println("");
                } else {
                    ui.manque();
                    joueurCourant = partie.changerJoueur(joueurCourant);
                }
            }catch(TirException e){
                System.err.println("Tir en dehors de la grille");
            }
        }

        ui.victoire(joueurCourant);
    }







    public static void saisieJoueur(List<BateauType> bateaux, UI ui, Partie partie, Joueur joueur){
        for(BateauType type: bateaux) {
            boolean placer = false;
            while(!placer) {
                ui.messagePlacerBateau(type);
                ui.messageRecupererPositionAvant();
                Position positionAvant = ui.recupererPosition();
                List<Position> positionArriereDispo = partie.positionsArriereDispo(positionAvant, type.nbCase());
                ui.messageRecupererPositionArriere(positionArriereDispo);
                Position positionArriere = ui.recupererPositionArriere(positionArriereDispo);
                try {
                    partie.placer(joueur, type, positionAvant, positionArriere);
                    placer = true;
                } catch (PlacerBateauException  exception) {
                    ui.messageErreurPlacementBateau(exception.getMessage());
                }
            }
        }
    }

}
