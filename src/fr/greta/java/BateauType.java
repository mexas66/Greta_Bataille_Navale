package fr.greta.java;

public enum BateauType {
    PORTE_AVION,CROISEUR,CONTRE_CROISEUR,SOUS_MARIN,TORPILLEUR;


    public int nbCase() {
        switch (this){
            case PORTE_AVION:
                return 5;

            case CROISEUR:
                return 4;

            case CONTRE_CROISEUR:
            case SOUS_MARIN:
                return 3;

            case TORPILLEUR:
                return 2;

            default:
                return 0;
        }
    }
}
