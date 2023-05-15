package gl.projet.itineraire;

import gl.projet.itineraire.Utils.Constants;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ItinaryApp {



    public static void main(String[] args) {
        System.out.println("Hello World!");

        // TODO : implémenter un jeu de données
    }

    // TODO : implémenter la méthode
    // Choix d'une station de destination parmi toutes celles définies
    // RODOLPHE
    public Station getDestination() {
        return null;
    }

    // TODO : implémenter la méthode
    // Recherche d'itinéraire favorite entre le plus rapide et le moins de changement de ligne
    // (regarder Constants.java)
    // MANU
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
        return null;
    }

    // TODO : implémenter la méthode
    // Position de départ aléatoire entre les limites définies (regarder
    // Constants.java)
    // RODOLPHE
    public Point getUserStartPosition() {
        return null;
    }
}
