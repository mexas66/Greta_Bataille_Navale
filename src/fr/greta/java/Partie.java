package fr.greta.java;

import java.util.ArrayList;
import java.util.List;

public class Partie {
    public Joueur joueur1;
    public Joueur joueur2;

    private ConverteurPosition converteur = new ConverteurPosition();

    private List<Bateau> armadaJoueur1 = new ArrayList<>();
    private List<Bateau> armadaJoueur2 = new ArrayList<>();



    public void placer(Joueur joueur, BateauType type, Position positionAvant, Position positionArriere) throws PlacerBateauException {
        try {

            if(!estDansLaGrille(positionAvant) || !estDansLaGrille(positionArriere)){
                throw new PlacerBateauException("Le positionnement ne correspond pas à la taille du bateau");
            }
            List<Position> positions = converteur.convertir(positionAvant, positionArriere);
            if(positions.size() != type.nbCase()) {
                throw new PlacerBateauException("Le positionnement ne correspond pas à la taille du bateau");
            }
            List<Bateau> armada = armada(joueur);
            for(Bateau chaqueBateauDejaPlace : armada) {
                if(chaqueBateauDejaPlace.traverse(positions)) {
                    throw new PlacerBateauException("Le positionnement chevauche un bateau");
                }
            }
            Bateau bateau = new Bateau();
            bateau.setType(type);
            bateau.placer(positions);
            armada.add(bateau);
        } catch (ConverteurException e) {
            throw new PlacerBateauException("Le positionnement ne correspond pas à la taille du bateau");
        }
    }

    private List<Bateau> armada(Joueur joueur) {
        if (joueur.equals(joueur1)){
            return armadaJoueur1;
        }
        return armadaJoueur2;
    }

    private boolean estDansLaGrille(Position position){
        if(position.getX() > 9 || position.getX() < 0 || position.getY() > 9 || position.getY() < 0){
            return false;
        }
        return true;
    }

    public List<BateauType> bateauAPlacer() {
        Regle regle = new Regle();
        List<BateauType> aPlacer = new ArrayList<>();
        try {
            aPlacer = regle.bateauTypeAPlacer();
        }catch(RegleBateauException e){
            System.err.println("Pas de bateau");
            System.exit(-1);
        }
        return aPlacer;
    }



    public boolean tirer(Joueur joueur, Position position) throws TirException{
        if(!estDansLaGrille(position)){
            throw new TirException();
        }
        if(joueur.equals(joueur1)){
            return viserArmada2(position);
        }else{
            return viserArmada1(position);
        }
    }


    private boolean viserArmada1(Position position) {
        for(Bateau bateau: armadaJoueur1){
            if(bateau.estSur(position)){
                bateau.mapUpdate(position);
                return true;
            }
        }
        return false;
    }


    private boolean viserArmada2(Position position) {
        for(Bateau bateau: armadaJoueur2){
            if(bateau.estSur(position)){
                bateau.mapUpdate(position);
                return true;
            }
        }
        return false;
    }

    public Joueur changerJoueur(Joueur joueur){
        if(joueur.equals(joueur1)){
            return joueur2;
        }
        return joueur1;
    }

    public boolean estTermine(Joueur joueur){
        if (joueur.equals(joueur1)){
            return checkArmada(armadaJoueur2);
        }
        return checkArmada(armadaJoueur1);
    }

    private boolean checkArmada(List<Bateau> armada){
        for(Bateau bateau : armada){
            if(!bateau.estCoule()){
                return false;
            }
        }
        return true;
    }

    public List<Position> positionsArriereDispo(Position position, int tailleBateau){
        List<Position> positionDispo = new ArrayList<>();

        positionDispo = positionHaut(positionDispo, position, tailleBateau);
        positionDispo = positionBas(positionDispo, position, tailleBateau);
        positionDispo = positionGauche(positionDispo, position, tailleBateau);
        positionDispo = positionDroite(positionDispo, position, tailleBateau);

        return positionDispo;
        }

    private List<Position> positionGauche(List<Position> positionDispo, Position position, int tailleBateau) {
        Position positionPossible = new Position(position.getX()-(tailleBateau-1), position.getY());
        if(estDansLaGrille(positionPossible)) {
            positionDispo.add(positionPossible);
        }
        return positionDispo;
    }

    private List<Position> positionDroite(List<Position> positionDispo, Position position, int tailleBateau) {
        Position positionPossible = new Position(position.getX()+(tailleBateau-1), position.getY());
        if(estDansLaGrille(positionPossible)) {
            positionDispo.add(positionPossible);
        }
        return positionDispo;
    }

    private List<Position> positionBas(List<Position> positionDispo, Position position, int tailleBateau) {
        Position positionPossible = new Position(position.getX(), position.getY()+(tailleBateau-1));
        if(estDansLaGrille(positionPossible)) {
            positionDispo.add(positionPossible);
        }
        return positionDispo;
    }


    private List<Position> positionHaut(List<Position> positionDispo, Position position, int tailleBateau) {
        Position positionPossible = new Position(position.getX(), position.getY()-(tailleBateau-1));
        if(estDansLaGrille(positionPossible)) {
            positionDispo.add(positionPossible);
        }

        return positionDispo;

    }

    public boolean checkBateauEstCoule(Joueur joueur, Position positionTir) {
        if (joueur.equals(joueur1)){
            return checkBateau(armadaJoueur2, positionTir);
        }
        return checkBateau(armadaJoueur1, positionTir);

    }

    private boolean checkBateau(List<Bateau> armada, Position positionTir) {
        for(Bateau bateau: armada ){
            if(bateau.estSur(positionTir)){
                for(Position position: bateau.getPositions().keySet()){
                    if(!bateau.getPositions().get(position)){
                        return false;
                    }
                }
            }
        }
        return true;
    }
}

