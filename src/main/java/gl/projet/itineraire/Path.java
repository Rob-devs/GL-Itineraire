package gl.projet.itineraire;

import java.util.ArrayList;
import java.util.List;

// Un chemin trouvé par l'application
public class Path {

    List<Road> roads;
    int travelTime;

    // Constructor
    public Path() {
        roads = new ArrayList<Road>();
        travelTime = 0;
    }

    public void addRoad(Road road) {
        roads.add(road);
    }

    public void addTravelTime(int travelTime) {
        this.travelTime += travelTime;
    }

    public boolean pathContainsStation(Station s) {
        for (Road r : this.roads) {
            if (r.getFirstStation() ==s ||r.getSecondStation() ==s)   {
                return true;
            }
        }
        return false;
    }

    // contiennent les mêmes stations (peu importe l'ordre)
    public boolean pathHasDuplicateRoad() {
        //parcours de la liste des chemins roads
        for (Road rRef : this.roads) {
            //Pour chaque Road de roads on va parcourir la liste pour voir s'il y a les mêmes Stations
            for (Road r : this.roads) {
                //Evalutation des stations
                boolean isSameStations = (rRef.getFirstStation() == r.getFirstStation() && rRef.getSecondStation() == r.getSecondStation())
                        || (rRef.getFirstStation() == r.getSecondStation() && rRef.getSecondStation() == r.getFirstStation());
                //Evaluation qui vérifie qu'on ne compare la rRef avec elle même.
                boolean isDifferentReference = rRef != r;

                if (isDifferentReference && isSameStations) {
                    return true;
                }
            }
        }
        return false;
    }

    // Si roads est null, renvoyer null
    public Station getLastStation() {
        try {
            return roads.get(roads.size() - 1).secondStation;
        }catch(NullPointerException e){
            return null;
        }
    }

    // #region Getters & Setters
    public List<Road> getRoads() {
        return roads;
    }

    public void setRoads(List<Road> roads) {
        this.roads = roads;
    }

    public int getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(int travelTime) {
        this.travelTime = travelTime;
    }
    // #endregion
}
