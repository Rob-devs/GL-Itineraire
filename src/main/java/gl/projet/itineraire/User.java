package gl.projet.itineraire;

import java.util.ArrayList;
import java.util.List;

// Utilisateur de l'application
public class User {

    Point startPosition;
    Station destination;
    List<Station> stationsToStop = new ArrayList<>();
    String preferredItinary;

    // Constructor
    public User(Point startPosition) {
        this.startPosition = startPosition;
    }

    // #region Getters & Setters
    public Point getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(Point startPosition) {
        this.startPosition = startPosition;
    }

    public Station getDestination() {
        return destination;
    }

    public void setDestination(Station destination) {
        this.destination = destination;
    }

    public List<Station> getStationsToStop() {
        return stationsToStop;
    }

    public void setStationsToStop(List<Station> stationsToStop) {
        this.stationsToStop = stationsToStop;
    }

    public String getPreferredItinary() {
        return preferredItinary;
    }

    public void setPreferredItinary(String preferredItinary) {
        this.preferredItinary = preferredItinary;
    }
    // #endregion
}
