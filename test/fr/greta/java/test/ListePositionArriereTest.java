package fr.greta.java.test;

import fr.greta.java.Partie;
import fr.greta.java.Position;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ListePositionArriereTest {

    @Test
    public void TestPositionBord(){
        Partie partie = new Partie();
        List<Position> positionTest = new ArrayList<>();
        positionTest.add(new Position(8,3));
        positionTest.add(new Position(8,7));
        positionTest.add(new Position(6,5));

        assertEquals(positionTest, partie.positionsArriereDispo(new Position(8,5), 3));
    }

    @Test
    public void TestPositionMilieu(){
        Partie partie = new Partie();
        List<Position> positionTest = new ArrayList<>();
        positionTest.add(new Position(5,2));
        positionTest.add(new Position(5,8));
        positionTest.add(new Position(2,5));
        positionTest.add(new Position(8,5));

        assertEquals(positionTest, partie.positionsArriereDispo(new Position(5,5), 4));
    }

    @Test
    public void TestPositionCoin(){
        Partie partie = new Partie();
        List<Position> positionTest = new ArrayList<>();
        positionTest.add(new Position(1,6));
        positionTest.add(new Position(4,9));

        assertEquals(positionTest, partie.positionsArriereDispo(new Position(1,9), 4));
    }
}
