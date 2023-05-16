package gl.projet.itineraire;

// Route allant d'une station à une autre station
public class Road {

    Station firstStation;
    Station secondStation;

    double distance;
    boolean isAccident;

    // Constructor
    public Road(Station firstStation, Station secondStation) {
        this.firstStation = firstStation;
        this.secondStation = secondStation;
        distance = firstStation.getDistance(secondStation);
        isAccident = false;
    }

    // #region Getters & Setters
    public Station getFirstStation() {
        return firstStation;
    }

    public Station getSecondStation() {
        return secondStation;
    }

    public void setFirstStation(Station firstStation) {
        this.firstStation = firstStation;
    }

    public void setSecondStation(Station secondStation) {
        this.secondStation = secondStation;
    }
    // #endregion
}
