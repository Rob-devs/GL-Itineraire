package gl.projet.itineraire;

import java.util.List;

public class Line {

    String id;

    List<Road> roads;

    // Constructor
    public Line(String id, List<Road> roads) {
        this.id = id;
        this.roads = roads;
    }

    // #region Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Road> getRoads() {
        return roads;
    }

    public void setRoads(List<Road> roads) {
        this.roads = roads;
    }
    // #endregion
}
