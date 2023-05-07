package gl.projet.itineraire;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
        ItinaryApp app = new ItinaryApp();
        Station s = app.getDestination();
        assert s != null;
        assert s.getName().length() > 0;
    }

    @Test
    @DisplayName("Get the user preferred itinary")
    public void getUserPreferredItinary() {
        ItinaryApp app = new ItinaryApp();
        String s = app.getPreferredItinary();
        assert (s == Constants.ITINARY_FASTEST || s == Constants.ITINARY_NO_CHANGE);
    }

    @Test
    @DisplayName("Get the user stations to stop")
    public void getUserStationsToStop() {
        ItinaryApp app = new ItinaryApp();
        List<Station> s = app.getStationsToStop();
        assert s != null;
    }
}
