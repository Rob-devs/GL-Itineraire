package gl.projet.itineraire;

// Point de coordonnées
public class Point {

    int x;
    int y;

    // Constructor
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Retourne la distance euclidienne entre deux points
    public double getDistance(Point p) {

        // Point sur lequel on appel getDistance
        int x1 = this.x;
        int y1 = this.y;

        // Point passé en param
        int x2 = p.getX();
        int y2 = p.getY();

        // Calcul de la distance
        return Math.sqrt(((x2 - x1) * (x2 - x1)) + ((y2 - y1) * (y2 - y1)));
    }

    // #region Getters & Setters
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    // #endregion
}
