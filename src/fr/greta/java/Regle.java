package fr.greta.java;

import java.util.ArrayList;
import java.util.List;

public class Regle {



    public List<BateauType> bateauTypeAPlacer() throws RegleBateauException{
        List<BateauType> listeDeBateaux = new ArrayList<>();

        UI ui = new UI();

            for (BateauType type : BateauType.values()) {
                boolean saisie = false;
                while (!saisie) {
                    try {
                        ui.demandeAjoutBateau(type);
                        if (ui.saisieAjoutBateau()) {
                            listeDeBateaux.add(type);
                        }
                        saisie = true;
                    } catch (RegleException e) {
                        System.err.println("Veuillez saisir O ou N");
                    }
                }
            }
            if(listeDeBateaux.size() == 0){
                throw new RegleBateauException();
            }
        return listeDeBateaux;
    }
}
