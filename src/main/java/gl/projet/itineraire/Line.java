package gl.projet.itineraire;

import java.util.List;

// Ligne de métro regroupant la suite de routes
public class Line {

    int id;
    int intervalle;

    List<Road> roads;

    // Constructor
    public Line(int id, List<Road> roads, int intervalle) {
        this.id = id;
        this.roads = roads;
        this.intervalle = intervalle;
    }

    // Temps à attendre pour prendre prendre cette ligne
    // Renvoie currentTime - n*intervalle pour que le retour soit entre 0 et
    // l'intervalle
    // Peut se faire avec un while
    public int getTimeToWait(int currentTime) {
        return this.intervalle-(currentTime%this.intervalle);
    }

    // #region Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Road> getRoads() {
        return roads;
    }

    public void setRoads(List<Road> roads) {
        this.roads = roads;
    }

    public int getIntervalle() {
        return intervalle;
    }

    public void setIntervalle(int intervalle) {
        this.intervalle = intervalle;
    }
    // #endregion
}
