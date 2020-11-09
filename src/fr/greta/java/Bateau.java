package fr.greta.java;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bateau {
    private BateauType type;
    private Map<Position, Boolean> positions;

    public Map<Position, Boolean> getPositions() {
        return positions;
    }

    public boolean traverse(List<Position> positions) {
        for(Position position : positions){
            if (estSur(position)){
                return true;
            }
        }
        return false;
    }


    public void setType(BateauType type) {
        this.type = type;
    }



    public boolean estSur(Position position){
        for(Position positionBateau : positions.keySet()){
            if (positionBateau.equals(position)){
                return true;
            }
        }
        return false;
    }

    public boolean estCoule(){
        for (Position positionBateau : positions.keySet()){
            if(!positions.get(positionBateau)){
                return false;
            }
        }
        return true;
    }

    public void mapUpdate(Position position) {
        positions.put(position, true);
    }

    public void placer(List<Position> positions) {
        Map<Position,Boolean> map = new HashMap<>();
        for(Position position: positions){
            map.put(position, false);
        }
        this.positions = map;
    }
}
