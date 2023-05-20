package gl.projet.itineraire;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
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
    }

}
