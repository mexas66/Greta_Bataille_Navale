package fr.greta.java.test;

import fr.greta.java.ConverteurException;
import fr.greta.java.ConverteurPosition;
import fr.greta.java.Position;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class ConverteurPositionTest {

    ConverteurPosition converteur = new ConverteurPosition();

    @Test
    public void testHorizontal() throws ConverteurException {

        List<Position> positions = new ArrayList<>();
        positions.add(new Position(2,3));
        positions.add(new Position(2,4));
        positions.add(new Position(2,5));
        positions.add(new Position(2,6));


        assertEquals(positions, converteur.convertir(new Position(2,3), new Position(2,6)));
    }

    @Test
    public void testVertical() throws ConverteurException {

        List<Position> positions = new ArrayList<>();
        positions.add(new Position(2,3));
        positions.add(new Position(3,3));
        positions.add(new Position(4,3));
        positions.add(new Position(5,3));

        assertEquals(positions, converteur.convertir(new Position(2,3), new Position(5,3)));
    }

    @Test(expected = ConverteurException.class)
    public void testConverteurException() throws ConverteurException {
        converteur.convertir(new Position(2,2), new Position(5,5)) ;
    }
}
