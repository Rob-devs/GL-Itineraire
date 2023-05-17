package gl.projet.itineraire;

import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import gl.projet.itineraire.Utils.Constants;

public class ObjectsTest {

    // #region Point
    @Test
    @DisplayName("Initialization of a point")
    public void initializePoint() {
        Point p = new Point(1, 2);
        assert p.getX() == 1;
        assert p.getY() == 2;

        p.setX(3);
        p.setY(4);
        assert p.getX() == 3;
        assert p.getY() == 4;
    }

    @Test
    @DisplayName("Get distance between two points")
    public void getPointDistance() {
        Point p = new Point(1, 1);
        Point q = new Point(1, 6);
        assert p.getDistance(q) == 5;

        p.setX(0);
        p.setY(3);
        q.setX(7);
        q.setY(3);
        assert p.getDistance(q) == 7;
    }
    // #endregion

    // #region Station
    @Test
    @DisplayName("Initialization of a station")
    public void initializeStation() {
        Station s = new Station("s", new Point(1, 1));
        assert s.getName() == "s";
        assert s.getPosition().getX() == 1;
        assert s.getPosition().getY() == 1;

        s.setAccident(true);
        assert s.isAccident() == true;

        s.setPosition(7, 3);
        assert s.getPosition().getX() == 7;
        assert s.getPosition().getY() == 3;

        s.setName("s2");
        assert s.getName() == "s2";
    }

    @Test
    @DisplayName("Get distance between a station and a point")
    public void getStationDistance() {
        Point p = new Point(1, 1);
        Station s = new Station("s", new Point(1, 6));
        assert s.getDistance(p) == 5;

        p.setX(0);
        p.setY(3);
        s.setPosition(7, 3);
        assert s.getDistance(p) == 7;
    }
    // #endregion

    // #region User
    @Test
    @DisplayName("Initialization of a new User")
    public void initializeUser() {
        User u = new User(new Point(1, 1));
        assert (u.getStartPosition().getX() == 1 && u.getStartPosition().getY() == 1);

        u.setDestination(new Station("s", new Point(1, 2)));
        assert u.getDestination().getName() == "s";

        u.setPreferredItinary(Constants.ITINARY_FASTEST);
        assert u.getPreferredItinary() == Constants.ITINARY_FASTEST;

        u.setStationsToStop(Arrays.asList(new Station("s1", new Point(1, 1)), new Station("s2", new Point(1, 3))));
        assert u.getStationsToStop().size() == 2;
    }
    // #endregion

    // #region Road
    @Test
    @DisplayName("Initialization of a new Road")
    public void initializeRoad() {
        Road r = new Road(new Station("s1", new Point(1, 1)), new Station("s2", new Point(1, 2)));
        assert r.getFirstStation().getPosition().getX() == 1;
        assert r.getFirstStation().getPosition().getY() == 1;
        assert r.getSecondStation().getPosition().getX() == 1;
        assert r.getSecondStation().getPosition().getY() == 2;
    }
    // #endregion

    // #region Line
    @Test
    @DisplayName("Initialization of a new Line")
    public void initializeLine() {
        Line l = new Line(1,
                Arrays.asList(new Road(new Station("s1", new Point(1, 1)), new Station("s2", new Point(1, 2))),
                        new Road(new Station("s3", new Point(2, 2)), new Station("s4", new Point(2, 1)))));
        assert l.getId() == 1;
        assert l.getRoads().size() == 2;
        assert l.getRoads().get(0).getFirstStation().getPosition().getX() == 1;
        assert l.getRoads().get(0).getFirstStation().getPosition().getY() == 1;
        assert l.getRoads().get(1).getFirstStation().getPosition().getX() == 2;
        assert l.getRoads().get(1).getSecondStation().getPosition().getY() == 1;
    }
    // #endregion

    // #region Line
    @Test
    @DisplayName("Initialization of a new Path")
    public void initializePath() {
        Path p = new Path();
        assert p.getRoads().size() == 0;
        assert p.getTravelTime() == 0;

        p.addTravelTime(23);
        assert p.getTravelTime() == 23;

        p.addRoad(new Road(new Station("s1", new Point(1, 1)), new Station("s2", new Point(1, 2))));
        assert p.getRoads().size() == 1;
    }
    // #endregion
}
