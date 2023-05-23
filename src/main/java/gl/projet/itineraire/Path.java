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

    // TODO : Renvoie true si un des chemins dans le trajet contient la station
    public boolean pathContainsStation(Station s) {
        return true;
    }

    // TODO : Renvoie true si dans la liste des chemins roads, deux chemins
    // contiennent les mêmes stations (peu importe l'ordre)
    public boolean pathHasDuplicateRoad() {
        return true;
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
