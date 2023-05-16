package gl.projet.itineraire;

import gl.projet.itineraire.Utils.Constants;

import java.util.*;

public class ItinaryApp {

    private static List<Station> listStation;

    private final Station destination;

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }

    public ItinaryApp(){

        Point palpha = new Point(1,1);
        Point pbeta = new Point(3,8);
        Point pcharlie = new Point(6,12);
        Point pdelta = new Point(1,29);
        Point pecho = new Point(12,5);
        Point pfoxtrot = new Point(17,2);
        Point pgolf = new Point(15,18);
        Point photel = new Point(29,1);
        Point pindia = new Point(23,25);
        Point pjuliett = new Point(20,27);
        Point pkilo = new Point(26,15);
        Point plima = new Point(29,29);

        Station alpha = new Station("alpha", palpha);
        Station bravo = new Station("beta", pbeta);
        Station charlie = new Station("charlie", pcharlie);
        Station delta = new Station("delta", pdelta);
        Station echo = new Station("echo", pecho);
        Station foxtrot = new Station("foxtrot", pfoxtrot);
        Station golf = new Station("golf", pgolf);
        Station hotel = new Station("hotel", photel);
        Station india = new Station("india", pindia);
        Station juliett = new Station("juliett", pjuliett);
        Station kilo = new Station("kilo", pkilo);
        Station lima = new Station("lima", plima);

        listStation = new ArrayList<>();

        listStation.add(alpha);
        listStation.add(bravo);
        listStation.add(charlie);
        listStation.add(delta);
        listStation.add(echo);
        listStation.add(foxtrot);
        listStation.add(golf);
        listStation.add(hotel);
        listStation.add(india);
        listStation.add(juliett);
        listStation.add(kilo);
        listStation.add(lima);

        this.destination = getDestination();
    }

    // Choix d'une station de destination parmi toutes celles définies
    public Station getDestination() {
        System.out.println("Veuillez choisir la destination que vous voulez : ");
        for(int i=0; i < listStation.size(); i++) {
            System.out.println((i+1) +  " - " + listStation.get(i).getName());
        }
        Scanner sc = new Scanner(System.in);
        try {
            int choice = sc.nextInt();
            if(choice <= listStation.size() && choice > 0) {
                return listStation.get(choice-1);
            }else {
                return getDestination();
            }
        } catch(InputMismatchException e) {
            return getDestination();
        }

    }

    // Recherche d'itinéraire favorite entre le plus rapide et le moins de changement de ligne
    public String getPreferredItinary() {
        System.out.println("Veuillez choisir quel type d'itinéraire vous préféré : ");
        System.out.println("1 - Le plus rapide");
        System.out.println("2 - Le moins de changement");

        Scanner scan = new Scanner(System.in);
        try {
            int choice = scan.nextInt();

            return switch (choice){
                case 1 -> Constants.ITINARY_FASTEST;
                case 2 -> Constants.ITINARY_NO_CHANGE;
                default -> getPreferredItinary();
            };
        }catch (InputMismatchException e){
            return getPreferredItinary();
        }
    }

    // TODO : implémenter la méthode
    // Recherche de la liste des stations auxquelles s'arrêter (à part la
    // destination)
    // Mettre une limite (3 maximum) ?
    // MANU
    public List<Station> getStationsToStop() {
        List<Station> arret = new ArrayList<>();
        for(int j = 0;j<3;j++) {
            System.out.println("A quelle station souhaitez-vous vous arrêter :");
            for (int i = 1; i <= listStation.size(); i++) {
                if (!(arret.contains(listStation.get(i-1))) && !(Objects.equals(listStation.get(i - 1).getName(), this.destination.getName()))){
                    System.out.println((i) + " - " + listStation.get(i-1).getName());
                }
            }

            System.out.println((listStation.size()+1)+" - Stop");

            Scanner scan = new Scanner(System.in);
            try {
                int choice = scan.nextInt();
                if(listStation.size()+1 == choice){
                    j = 4;
                }else{
                    if(0<choice && choice<listStation.size()) {
                        if(!(arret.contains(listStation.get(choice-1))) && !(Objects.equals(listStation.get(choice - 1).getName(), destination.getName()))) {
                            arret.add(listStation.get(choice - 1));
                        }else{
                            j--;
                            System.out.println("Veuillez choisir une option valable");
                        }
                        System.out.println(arret.get(j).getName());
                    }else{
                        j--;
                        System.out.println("Veuillez choisir une option valable");
                    }
                }
            }catch (InputMismatchException e){
                j--;
                System.out.println("Veuillez choisir une option valable");
            }
        }
        return arret;
    }

    // Position de départ aléatoire entre les limites définies (regarder
    // Constants.java)
    // RODOLPHE
    public Point getUserStartPosition() {
        int max = Constants.MAX_START_POSITION;
        int min = Constants.MIN_START_POSITION;
        Random random = new Random();
        //random.nextInt(n) returns a random int between 0 and n-1
        return new Point(random.nextInt((max - min) + 1) + min,random.nextInt((max - min) + 1) + min);
    }
}
