package gl.projet.itineraire;

import gl.projet.itineraire.Utils.Constants;

import java.util.*;

/**
 * The ItinaryApp class contains methods for creating and manipulating data
 * related to a transportation itinerary app.
 */
public class ItinaryApp {

    private static User user = new User(getUserStartPosition());

    private static List<Station> listStation = Arrays.asList(new Station("alpha", new Point(1, 1)),
            new Station("bravo", new Point(3, 8)), new Station("charlie", new Point(6, 12)),
            new Station("delta", new Point(1, 29)), new Station("echo", new Point(12, 5)),
            new Station("foxtrot", new Point(17, 2)), new Station("golf", new Point(15, 18)),
            new Station("hotel", new Point(29, 1)), new Station("india", new Point(23, 25)),
            new Station("juliett", new Point(20, 27)), new Station("kilo", new Point(26, 15)),
            new Station("lima", new Point(27, 7)), new Station("mike", new Point(8, 27)),
            new Station("november", new Point(4, 20)), new Station("oscar", new Point(29, 29)));
    private static List<Line> listLines = Arrays.asList(
            new Line(1, Arrays.asList(new Road(listStation.get(0), listStation.get(4)),
                    new Road(listStation.get(4), listStation.get(10)),
                    new Road(listStation.get(10), listStation.get(8))), 600),
            new Line(2, Arrays.asList(new Road(listStation.get(1), listStation.get(2)),
                    new Road(listStation.get(2), listStation.get(6)),
                    new Road(listStation.get(6), listStation.get(9))), 600),
            new Line(3, Arrays.asList(new Road(listStation.get(13), listStation.get(1)),
                    new Road(listStation.get(1), listStation.get(5)),
                    new Road(listStation.get(5), listStation.get(10)),
                    new Road(listStation.get(10), listStation.get(8))), 600),
            new Line(4, Arrays.asList(new Road(listStation.get(3), listStation.get(12)),
                    new Road(listStation.get(12), listStation.get(6)),
                    new Road(listStation.get(6), listStation.get(10)),
                    new Road(listStation.get(10), listStation.get(11)),
                    new Road(listStation.get(11), listStation.get(7))), 300));;

    private static Station destination = new Station();
    private static List<Path> paths;

    /******************************************************/
    /* ----------------- MAIN FUNCTION ------------------ */
    /******************************************************/
    public static void main(String[] args) {

        Point startPosition = user.getStartPosition();

        System.out.println("Calcule itineraire");
        System.out.println("Votre position de départ : )");
        System.out.println("x:" + startPosition.x);
        System.out.println("y:" + startPosition.y);

        user.setDestination(getDestination());

        List<Station> listStationsToStop = new ArrayList<Station>();

        Scanner scan = new Scanner(System.in);
        String choix = "";
        while (!choix.equals("N") && !choix.equals("n") && !choix.equals("Y") && !choix.equals("y")) {
            System.out.println("Voulez-vous ajouter des stations par lequelles l'itinéraire devra passer ? [Y/N]");
            choix = scan.nextLine();
            if (!choix.equals("N") && !choix.equals("n") && !choix.equals("Y") && !choix.equals("y")) {
                System.out.println("Erreur : choix invalide !" + '(' + choix + ')');
            }
        }
        scan.close();

        if (choix.equals("Y") || choix.equals("y")) {
            listStationsToStop = getStationsToStop();
        }
        user.setPreferredItinary(getPreferredItinary());

        generateAccidents();
        paths = getAllPathsFromStations(getStationsNearUser());

        // TODO : Rodolphe / Robin
        // Terminer le main
    }

    /******************************************************/
    /* -------------------- FEATURES -------------------- */
    /******************************************************/

    /**
     * This Java function prompts the user to choose a destination from a list of
     * stations and returns the selected station.
     *
     * @return This method returns a Station object, which represents the
     *         destination chosen by the user from a list of stations.
     */
    public static Station getDestination() {
        System.out.println("Veuillez choisir la destination que vous voulez : ");
        for (int i = 0; i < listStation.size(); i++) {
            System.out.println((i + 1) + " - " + listStation.get(i).getName());
        }
        Scanner sc = new Scanner(System.in);
        try {
            int choice = sc.nextInt();
            if (choice <= listStation.size() && choice > 0) {
                return listStation.get(choice - 1);
            } else {
                return getDestination();
            }
        } catch (InputMismatchException e) {
            return getDestination();
        } finally {
            sc.close();
        }
    }

    /**
     * This Java function prompts the user to choose their preferred type of
     * itinerary and returns a constant value based on their choice.
     *
     * @return The method returns a String representing the user's preferred
     *         itinerary type, either "fastest" or "no change". If the user enters
     *         an invalid input, the method recursively calls itself until a valid
     *         input is received.
     */
    public static String getPreferredItinary() {
        System.out.println("Veuillez choisir quel type d'itinéraire vous préféré : ");
        System.out.println("1 - Le plus rapide");
        System.out.println("2 - Le moins de changement");

        Scanner scan = new Scanner(System.in);
        try {
            int choice = scan.nextInt();

            return switch (choice) {
                case 1 -> Constants.ITINARY_FASTEST;
                case 2 -> Constants.ITINARY_NO_CHANGE;
                default -> getPreferredItinary();
            };
        } catch (InputMismatchException e) {
            return getPreferredItinary();
        } finally {
            scan.close();
        }
    }

    /**
     * This Java function allows a user to select multiple stations to stop at from
     * a list of available stations.
     *
     * @return A List of Station objects named "arret" is being returned.
     */
    public static List<Station> getStationsToStop() {

        List<Station> arret = new ArrayList<>();
        for (int j = 0; j < 3; j++) {
            System.out.println("A quelle station souhaitez-vous vous arrêter :");
            for (int i = 1; i <= listStation.size(); i++) {
                if (!(arret.contains(listStation.get(i - 1)))
                        && !(Objects.equals(listStation.get(i - 1).getName(), destination.getName()))) {
                    System.out.println((i) + " - " + listStation.get(i - 1).getName());
                }
            }

            System.out.println((listStation.size() + 1) + " - Stop");

            Scanner scan = new Scanner(System.in);
            try {
                int choice = scan.nextInt();
                if (listStation.size() + 1 == choice) {
                    j = 4;
                } else {
                    if (0 < choice && choice < listStation.size()) {
                        if (!(arret.contains(listStation.get(choice - 1)))
                                && !(Objects.equals(listStation.get(choice - 1).getName(), destination.getName()))) {
                            arret.add(listStation.get(choice - 1));
                        } else {
                            j--;
                            System.out.println("Veuillez choisir une option valable");
                        }
                        System.out.println(arret.get(j).getName());
                    } else {
                        j--;
                        System.out.println("Veuillez choisir une option valable");
                    }
                }
            } catch (InputMismatchException e) {
                j--;
                System.out.println("Veuillez choisir une option valable");
            } finally {
                scan.close();
            }
        }
        return arret;
    }

    /**
     * The function returns a random Point object within a specified range for the
     * user's starting position.
     *
     * @return A randomly generated Point object with x and y coordinates within a
     *         certain range defined by Constants.MAX_START_POSITION and
     *         Constants.MIN_START_POSITION.
     */
    public static Point getUserStartPosition() {

        int max = Constants.MAX_START_POSITION;
        int min = Constants.MIN_START_POSITION;

        Random random = new Random();
        // random.nextInt(n) returns a random int between 0 and n-1
        return new Point(random.nextInt((max - min) + 1) + min, random.nextInt((max - min) + 1) + min);
    }

    /**
     * This function calculates the time in seconds it takes to travel a certain
     * distance based on a
     * constant conversion factor.
     * 
     * @param distance The distance parameter is a double value representing a
     *                 distance measurement.
     * @return The method is returning an integer value which represents the number
     *         of seconds it would
     *         take to travel the given distance. The calculation is done by
     *         multiplying the distance by a
     *         constant value that converts distance to seconds.
     */
    public static int getSecondsFromDistance(double distance) {
        return (int) (distance * Constants.CONVERT_DIST_TO_SECONDS);
    }

    /**
     * This Java function returns a list of the 5 nearest stations to a user's
     * starting position.
     * 
     * @return A list of the 5 nearest stations to the user's starting position.
     */
    public static List<Station> getStationsNearUser() {
        HashMap<Double, Station> distance = new HashMap<>();
        List<Station> res = new ArrayList<>();
        for (Station s : listStation) {
            if (!s.isAccident()) {
                distance.put(s.getPosition().getDistance(user.startPosition), s);
            }
        }
        List<Double> sort = distance.keySet().stream().sorted().toList();
        for (int i = 0; i < 5; i++) {
            res.add(distance.get(sort.get(i)));
        }

        return res;
    }

    /**
     * This function returns a list of roads that are near a given station and do
     * not have any
     * accidents.
     * 
     * @param s The parameter "s" is an object of the class Station, which
     *          represents a station on a
     *          transportation network. It is used as a reference point to find the
     *          roads that are near it.
     * @return The method is returning a list of roads that are near the given
     *         station and do not have
     *         any accidents.
     */
    public static List<Road> getRoadsNearStation(Station s) {
        List<Road> roadsNearStation = new ArrayList<>();
        for (Line l : listLines) {
            for (Road r : l.getRoads()) {
                // Vérifier si la route n'a pas d'accident
                if (!r.isAccident() && !r.getFirstStation().isAccident() && !r.getSecondStation().isAccident()) {
                    // Vérifier si la station donnée est l'une des extrémités de la route
                    if (s.equals(r.getFirstStation())) {
                        roadsNearStation.add(new Road(s, r.getSecondStation()));
                    } else if (s.equals(r.getSecondStation()))
                        roadsNearStation.add(new Road(s, r.getFirstStation()));
                }
            }
        }
        return roadsNearStation;
    }

    // Trouver tous les trajets possibles à partir d'une station.
    public static List<Path> getAllPathsFromStation(Station s) {
        List<Path> listPath = new ArrayList<>();
        for (Road r : getRoadsNearStation(s)) {
            Path p = new Path();
            p.addRoad(r);
            listPath.add(p);
        }

        boolean terminer = false;

        while (!terminer) {
            List<Path> newListPath = new ArrayList<>();
            terminer = true;
            for (Path p : listPath) {
                if (p.pathContainsStation(user.getDestination())) {
                    newListPath.add(p);
                } else {
                    if (!p.pathHasDuplicateRoad()) {
                        terminer = false;
                        for (Road r : getRoadsNearStation(p.getLastStation())) {
                            Path path = p;
                            path.addRoad(r);
                            newListPath.add(path);
                        }
                    }
                }
            }
            listPath = newListPath;
        }

        return listPath;
    }

    // Remonter tous les trajets possibles de toutes les stations de départ
    // possibles de l'utilisateur.
    // Appeler pour chaque station getAllPathsFromStation et remonter toutes les
    // listes en une seule.
    public static List<Path> getAllPathsFromStations(List<Station> stations) {
        List<Path> result = new ArrayList<>();
        for (Station s : stations) {
            result.addAll(getAllPathsFromStation(s));
        }
        return result;
    }

    /**
     * This function generates random accidents in stations and on roads.
     */
    public static void generateAccidents() {
        Random randStation = new Random();
        System.out.println("Accidents dans les stations :");
        for (Station s : listStation) {
            int numAccidents = randStation.nextInt(15);
            if (numAccidents <= 2 && numAccidents >= 0) {
                s.setAccident(true);
            } else {
                s.setAccident(false);
            }
            System.out.println("Station " + s.getName() + ":" + s.isAccident());
        }
        System.out.println("Accidents sur les routes :");
        Random randRoad = new Random();
        for (Line l : listLines) {
            for (Road r : l.getRoads()) {
                int numAccidents = randRoad.nextInt(5);
                if (numAccidents == 1) {
                    r.setAccident(true);
                } else {
                    r.setAccident(false);
                }
                System.out.println(
                        "Route entre " + r.getFirstStation() + " et " + r.getSecondStation() + " : " + r.isAccident());
            }
        }
    }

    /**
     * This function calculates the total travel time and number of station changes
     * for a given path.
     *
     * @param path A Path object representing a route between two stations.
     */
    public static void setTimeToPath(Path path) {
        int totalTime = 0;
        int totalLineChanges = 0;
        totalTime += getTimeFromStartToFirstStation(user.getStartPosition(), path);
        int idline = getLineFromRoad(path.getRoads().get(0)).getId();
        for (Road road : path.getRoads()) {
            totalTime += getSecondsFromDistance(road.getDistance());
            Line newL = getLineFromRoad(road);
            if (idline != newL.getId()) {
                totalLineChanges++;
                idline = newL.getId();
                totalTime += newL.getTimeToWait(totalTime);
            } else {
                totalTime += Constants.STATION_STOP_TIME;
            }
        }
        path.setTravelTime(totalTime);
        path.setStationChanges(totalLineChanges);
    }

    /**
     * This function returns a Line object that contains a given Road object.
     *
     * @param road The road parameter is an object of the Road class, which
     *             represents a road
     *             connecting two stations in a transportation system. It has two
     *             Station objects as its endpoints.
     * @return The method is returning a Line object. If a Line object is found in
     *         the list of lines
     *         that contains the given Road object, it will be returned. Otherwise,
     *         null will be returned.
     */
    public static Line getLineFromRoad(Road road) {
        for (Line l : listLines) {
            for (Road r : l.getRoads()) {
                if ((r.getFirstStation() == road.getFirstStation() && r.getSecondStation() == road.getSecondStation())
                        || (r.getFirstStation() == road.getSecondStation()
                                && r.getSecondStation() == road.getFirstStation())) {
                    return l;
                }
            }
        }
        return null;
    }

    /**
     * This function sets the time to each path in a list of paths.
     */
    public static void setTimeToPaths() {
        for (Path p : paths) {
            setTimeToPath(p);
        }
    }

    // Obtenir le meilleur trajet de la liste des trajets selon la preference de
    // l'utilisateur
    public static Path getBestPath(List<Path> paths, String preference) {
        Path bestPath = new Path();
        int nbstation = user.getStationsToStop().size();
        for (Path p: paths) {
            int i = (int) listStation.stream().filter(p::pathContainsStation).count();
            if(i == nbstation){
                switch (preference){
                    case Constants.ITINARY_FASTEST -> {
                        if(p.getTravelTime()<bestPath.getTravelTime() || bestPath.getTravelTime() == 0){
                            bestPath = p;
                        }
                    }
                    case Constants.ITINARY_NO_CHANGE -> {
                        if(p.getStationChanges()<bestPath.getStationChanges() || bestPath.getTravelTime() == 0){
                            bestPath = p;
                        }
                    }
                    default -> {bestPath = null;}
                }
            }
        }

        return bestPath;
    }

    // Obtient le temps mit à parcourir la distance entre la position de
    // l'utilisateur et la première station du trajet
    public static int getTimeFromStartToFirstStation(Point startPosition, Path path) {
        // On récupère la position de la première stations du path
        Road firstRoad = path.roads.get(0);
        Point firstStation = firstRoad.firstStation.getPosition();
        // On calul la distance entre les deux
        double dist = startPosition.getDistance(firstStation);
        // On converti en temps de trajet
        return getSecondsFromDistance(dist);
    }

    // TODO : Rodolphe / Robin
    // Afficher les informations principales d'un path :
    // Temps de trajet et nombre de changement de lignes
    public static void displayPathInfos(Path path) {

    }

    // TODO : Rodolphe / Robin
    // Afficher les chemins et lignes d'un path :
    // Afficher chaque station les unes après les autres et indiquer à chaque fois
    // qu'on change de ligne par l'ID de la ligne à prendre
    public static void displayPathRoads(Path path) {
        // Exemple attendu (dans l'idée) :
        //
        // Marcher juqu'à Station etoile -> prendre Ligne 3
        // Station fleur...
        // Station caillou...
        // Station test...
        // Station x -> changement -> Ligne 2
        // Station y...
        // Station destination !
    }
}
