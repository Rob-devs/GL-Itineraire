package gl.projet.itineraire;

public class Road {

    Station firstStation;
    Station secondStation;

    double distance;

    // Constructor
    public Road(Station firstStation, Station secondStation) {
        this.firstStation = firstStation;
        this.secondStation = secondStation;
        distance = firstStation.getDistance(secondStation);
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
