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

    // contiennent les mêmes stations (peu importe l'ordre)
    public boolean pathHasDuplicateRoad() {
        //parcours de la liste des chemins roads
        for (Road rRef : this.roads) {
            //Pour chaque Road de roads on va parcourir la liste pour voir s'il y a les mêmes Stations
            for (Road r : this.roads) {
                if(rRef!=r){
                    if(rRef.getFirstStation()==r.getFirstStation()&&rRef.getSecondStation()==r.getSecondStation()){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // TODO : Renvoie la seconde station du dernier chemin de roads
    // Si roads est null, renvoyer null
    public Station getLastStation() {
        return null;
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
