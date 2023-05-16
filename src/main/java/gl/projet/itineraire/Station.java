package gl.projet.itineraire;

// Station de métro
public class Station {

    String name;
    Point position;
    boolean accident;

    // Constructor
    public Station(String name, Point position) {
        this.name = name;
        this.position = position;
        this.accident = false;
    }

    public double getDistance(Point p) {
        return position.getDistance(p);
    }

    public double getDistance(Station s) {
        return position.getDistance(s.getPosition());
    }

    // #region Getters & Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(int x, int y) {
        this.position.setX(x);
        this.position.setY(y);
    }

    public boolean isAccident() {
        return accident;
    }

    public void setAccident(boolean accident) {
        this.accident = accident;
    }
    // #endregion
}
