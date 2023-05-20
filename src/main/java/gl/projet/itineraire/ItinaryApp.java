package gl.projet.itineraire;

import gl.projet.itineraire.Utils.Constants;

import java.util.*;

/**
 * The ItinaryApp class contains methods for creating and manipulating data
 * related to a transportation itinerary app.
 */
public class ItinaryApp {

    private static User user;

    private static List<Station> listStation;
    private static List<Line> listLines;
    private static Station destination;

    // Constructor
    public ItinaryApp() {

        createAppData();
        user = new User(getUserStartPosition());
        destination = new Station("", new Point(0, 0));

    }

    /******************************************************/
    /* ----------------- MAIN FUNCTION ------------------ */
    /******************************************************/
    public static void main(String[] args) {

        ItinaryApp app = new ItinaryApp();
        Point startPosition = user.getStartPosition();
        System.out.println("Calcule itineraire");
        System.out.println("Votre position de départ : )");
        System.out.println("x:" + startPosition.x);
        System.out.println("y:" + startPosition.y);
        app.getDestination();
        Scanner scan = new Scanner(System.in);
        String choix = "";
        while (!choix.equals("N") && !choix.equals("n") && !choix.equals("Y") && !choix.equals("y")) {
            System.out.println("Voulez-vous ajouter des stations par lequelles l'itinéraire devra passer ? [Y/N]");
            choix = scan.nextLine();
            if (!choix.equals("N") && !choix.equals("n") && !choix.equals("Y") && !choix.equals("y")) {
                System.out.println("Erreur : choix invalide !" + '(' + choix + ')');
            }
        }
        if (choix.equals("Y") || choix.equals("y")) {
            app.getStationsToStop();
        }
        String itinerary = app.getPreferredItinary();
    }

    /******************************************************/
    /* -------------------- FEATURES -------------------- */
    /******************************************************/

    /**
     * The function creates a list of stations and a list of lines with their
     * respective roads and
     * travel times.
     */
    private void createAppData() {

        listStation = Arrays.asList(
                new Station("alpha", new Point(1, 1)),
                new Station("bravo", new Point(3, 8)),
                new Station("charlie", new Point(6, 12)),
                new Station("delta", new Point(1, 29)),
                new Station("echo", new Point(12, 5)),
                new Station("foxtrot", new Point(17, 2)),
                new Station("golf", new Point(15, 18)),
                new Station("hotel", new Point(29, 1)),
                new Station("india", new Point(23, 25)),
                new Station("juliett", new Point(20, 27)),
                new Station("kilo", new Point(26, 15)),
                new Station("lima", new Point(27, 7)),
                new Station("mike", new Point(8, 27)),
                new Station("november", new Point(4, 20)),
                new Station("oscar", new Point(29, 29)));
        listLines = Arrays.asList(
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
                        new Road(listStation.get(11), listStation.get(7))), 300));
    }

    /**
     * This Java function prompts the user to choose a destination from a list of
     * stations and returns the selected station.
     *
     * @return This method returns a Station object, which represents the
     *         destination chosen by the user from a list of stations.
     */
    public Station getDestination() {
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
    public String getPreferredItinary() {
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
        }
    }

    /**
     * This Java function allows a user to select multiple stations to stop at from
     * a list of available stations.
     *
     * @return A List of Station objects named "arret" is being returned.
     */
    public List<Station> getStationsToStop() {

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
    public Point getUserStartPosition() {

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
    public int getSecondsFromDistance(double distance) {
        return (int) (distance * Constants.CONVERT_DIST_TO_SECONDS);
    }

    /**
     * This Java function returns a list of the 5 nearest stations to a user's
     * starting position.
     * 
     * @return A list of the 5 nearest stations to the user's starting position.
     */
    // TODO : Ignorer les stations accidentées
    public List<Station> getStationsNearUser() {
        HashMap<Double, Station> distance = new HashMap<>();
        List<Station> res = new ArrayList<>();
        for (Station s : listStation) {
            distance.put(s.getPosition().getDistance(user.startPosition), s);
        }

        List<Double> sort = distance.keySet().stream().sorted().toList();
        for (int i = 0; i < 5; i++) {
            res.add(distance.get(sort.get(i)));
        }

        return res;
    }

    // TODO : Obtenir tous les chemins (Road) qui sont accessibles à partir d'une
    // station.
    // Il faut pour ça regarder dans l'objet listLines qui décrit tous les chemins
    // possibles, et remonter tous les chemins qui contiennent la station.
    // Remonter tous les objets Road avec :
    // - Road.firstStation = la station d'origine,
    // - Road.secondStation = la station de destination.
    // Ne pas remonter les objets Road qui ont un accident ou dont une des stations
    // est accidentée.
    public List<Road> getRoadsNearStation(Station s) {
        return null;
    }

    // TODO : Trouver tous les trajets possibles à partir d'une station.
    public List<Path> getAllPathsFromStation(Station s) {
        return null;
    }

    // TODO : Remonter tous les trajets possibles de toutes les stations de départ
    // possibles de l'utilisateur.
    // Appeler pour chaque station getAllPathsFromStation et remonter toutes les
    // listes en une seule.
    public List<Path> getAllPathsFromStations(List<Station> stations) {
        return null;
    }

    // TODO : Créer aléatoirement des accidents, qui peuvent survenir dans les
    // objets Station et Road.
    // Il faut trouver une façon de faire qui génère un nombre d'accidents assez
    // stable (entre 0 et 3 ou 4)
    public void generateAccidents() {

    }
}
