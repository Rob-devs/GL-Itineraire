package gl.projet.itineraire;

import java.util.ArrayList;
import java.util.List;

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
