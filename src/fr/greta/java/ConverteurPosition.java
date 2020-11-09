package fr.greta.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ConverteurPosition {

    public List<Position> convertir(Position positionAvant, Position positionArriere) throws ConverteurException {

        if (positionAvant.getX() != positionArriere.getX() && positionAvant.getY() != positionArriere.getY()) {
            throw new ConverteurException();
        }

        List<Position> positions = new ArrayList<>();

        if (positionAvant.getX() == positionArriere.getX()) {
            for (int i = Math.min(positionAvant.getY(), positionArriere.getY()); i <= Math.max(positionAvant.getY(), positionArriere.getY()); i++) {
                positions.add(new Position(positionArriere.getX(), i));
            }

        }

        if (positionAvant.getY() == positionArriere.getY()) {
            for (int i = Math.min(positionAvant.getX(), positionArriere.getX()); i <= Math.max(positionAvant.getX(), positionArriere.getX()); i++) {
                positions.add(new Position(i, positionAvant.getY()));
            }
        }
        return positions;
    }
}
