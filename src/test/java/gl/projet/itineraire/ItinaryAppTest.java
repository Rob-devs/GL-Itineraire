package gl.projet.itineraire;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import gl.projet.itineraire.Utils.Constants;

public class ItinaryAppTest {

    @Test
    @DisplayName("Get the user start position")
    public void getUserStartPosition() {
        ItinaryApp app = new ItinaryApp();
        Point p = app.getUserStartPosition();
        assert p != null;
        assert (p.getX() <= Constants.MAX_START_POSITION && p.getX() >= Constants.MIN_START_POSITION
                && p.getY() <= Constants.MAX_START_POSITION && p.getY() >= Constants.MIN_START_POSITION);
    }

    @Test
    @DisplayName("Get the user destination")
    public void getUserDestination() {

        // MOCK System.in
        String data = "3";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        ItinaryApp app = new ItinaryApp();
        Station s = app.getDestination();

        System.setIn(stdin);

        assert s != null;
        assert s.getName().length() > 0;
    }

    @Test
    @DisplayName("Get the user preferred itinary")
    public void getUserPreferredItinary() {

        // MOCK System.in
        String data = "2";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        ItinaryApp app = new ItinaryApp();
        String s = app.getPreferredItinary();

        System.setIn(stdin);

        assert (s == Constants.ITINARY_FASTEST || s == Constants.ITINARY_NO_CHANGE);
    }

    @Test
    @DisplayName("Get the user stations to stop")
    public void getUserStationsToStop() {

        // MOCK System.in
        String data = "16";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        ItinaryApp app = new ItinaryApp();
        List<Station> s = app.getStationsToStop();

        System.setIn(stdin);

        assert s != null;
    }

    @ParameterizedTest
    @DisplayName("Convert distance to time")
    @ValueSource(doubles = { 0.5, 2, 6, 19, 1.1, 50, 1000 })
    public void getConvertionDistToTime(double distance) {
        ItinaryApp app = new ItinaryApp();
        assert (app.getSecondsFromDistance(distance) == (int) (distance * Constants.CONVERT_DIST_TO_SECONDS));
    }

    @Test
    @DisplayName("Get the 5 stations near the user")
    public void getStationsNearUser() {

        ItinaryApp app = new ItinaryApp();
        List<Station> s = app.getStationsNearUser();

        assert s != null;
        assert s.size() == 5;

        for (Station station : s) {
            assert station.isAccident() == false;
        }
    }

    @Test
    @DisplayName("Get the roads that contains the station")
    public void getRoadsNearStationTest() {

        ItinaryApp app = new ItinaryApp();
        Station s = new Station("bravo", new Point(3, 8));
        List<Road> roads = app.getRoadsNearStation(s);

        assert roads != null;

        if (roads != null && roads.size() > 0) {
            for (Road road : roads) {
                assert (road.getFirstStation() == s || road.getSecondStation() == s);
            }
        }
    }

    @Test
    @DisplayName("Get all the paths starting from the station")
    public void getAllPathsFromStationTest() {

        ItinaryApp app = new ItinaryApp();
        Station s = new Station("bravo", new Point(3, 8));
        List<Path> paths = app.getAllPathsFromStation(s);

        assert paths != null;

        if (paths != null && paths.size() > 0) {
            for (Path path : paths) {
                assert path.pathContainsStation(s);
            }
        }
    }

    @Test
    @DisplayName("Get all the paths starting from all the stations near the user")
    public void getAllPathsFromStationsTest() {

        ItinaryApp app = new ItinaryApp();
        Station s1 = new Station("bravo", new Point(3, 8));
        Station s2 = new Station("lima", new Point(27, 7));
        List<Path> paths = app.getAllPathsFromStations(Arrays.asList(s1, s2));

        assert paths != null;

        if (paths != null && paths.size() > 0) {
            for (Path path : paths) {
                assert path.pathContainsStation(s1) || path.pathContainsStation(s2);
            }
        }
    }

    @Test
    @DisplayName("Get the time to wait to take a line")
    public void getTimeToWaitTest() {

        Line l = new Line(1, null, 600);

        assert l.getTimeToWait(480) == 120;
        assert l.getTimeToWait(800) == 400;
    }

    @Test
    @DisplayName("Get the time for the user to go to the first station")
    public void getTimeFromStartToFirstStationTest() {

        ItinaryApp app = new ItinaryApp();
        Path p = new Path();
        p.addRoad(new Road(new Station("s1", new Point(1, 1)), new Station("s2", new Point(1, 2))));

        assert app.getTimeFromStartToFirstStation(p) > 0;
    }

    @Test
    @DisplayName("Get the best path of the list of paths")
    public void getBestPathTest() {

        ItinaryApp app = new ItinaryApp();
        Path p1 = new Path();
        p1.setTravelTime(500);
        p1.setStationChanges(3);
        Path p2 = new Path();
        p2.setTravelTime(600);
        p2.setStationChanges(1);

        assert app.getBestPath(Arrays.asList(p1, p2), Constants.ITINARY_FASTEST) == p1;
        assert app.getBestPath(Arrays.asList(p1, p2), Constants.ITINARY_NO_CHANGE) == p2;
    }

}
