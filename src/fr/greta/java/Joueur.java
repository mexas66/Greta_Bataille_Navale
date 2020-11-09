package fr.greta.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Joueur {
    public String pseudo;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Joueur joueur = (Joueur) o;
        return Objects.equals(pseudo, joueur.pseudo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pseudo);
    }
}
