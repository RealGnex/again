package metoda.newtona;

import java.util.ArrayList;
import java.util.Scanner;

class Punkt {

    public double x, y;
    public Punkt(double x, double y) {
        this.x = x; this.y = y;
    }
}

public class Main {

    public static void main(String[] args) {
        ArrayList<Punkt> punkty = new ArrayList<>();
        double podanyX, podanyY;
        int iloscPunktow;
        Scanner sc = new Scanner(System.in);

        System.out.println("Ile punktów chcesz wprowadzić?");
        iloscPunktow = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < iloscPunktow; i++) {
            System.out.println("Podaj wartość X:");
            podanyX = Double.parseDouble(sc.nextLine());
            System.out.println("Podaj wartość Y:");
            podanyY = Double.parseDouble(sc.nextLine());
            punkty.add(new Punkt(podanyX,podanyY));
            System.out.println("Punkt numer " + (i+1) + " został dodany!");
        }
        System.out.println("Szukany X? :");
        double[] szukanyX = new double[1];
        szukanyX[0] = Double.parseDouble(sc.nextLine());
        interpolacjaNewtona(szukanyX,punkty);
    }

    private static double[] ilorazRoznicowy(ArrayList<Punkt> punkty) {
        final int dlugosc = punkty.size();
        double[][] ilorazy = new double[dlugosc][]; //tworzenie kolumn
        //tworzenie wierszy
        for (int i = 0; i < dlugosc; i++)
            ilorazy[i] = new double[dlugosc-i];
        //ustawienie pierwszego wiersza
        for (int i = 0; i < dlugosc; i++)
            ilorazy[i][0] = punkty.get(i).y;
        //wypełnianie reszty wierszy
        for (int i = 1; i < dlugosc; i++) {
            for (int j = 0; j < dlugosc - i; j++) {
                ilorazy[j][i] = ((ilorazy[j+1][i-1] - ilorazy[j][i-1])
                        / (punkty.get(i+j).x - punkty.get(j).x));
            }
        }
        double[] wiersz = ilorazy[0];
        return wiersz;
    }

    private static void interpolacjaNewtona(double[] szukanyX, ArrayList<Punkt> punkty) {
        final int iloscPunktow = punkty.size();
        double[] ilorazy = ilorazRoznicowy(punkty);
        double y = ilorazy[iloscPunktow-1];
        for (int i = 0; i < szukanyX.length; i++) {
            for (int j = iloscPunktow - 2; j >= 0; j--)
                y = y * (szukanyX[i] - punkty.get(j).x) + ilorazy[j];
            System.out.println("Interpolacja w punkcie " + szukanyX[i] + " to " + y);
            }
        }

}
