package model;

import javafx.scene.paint.Color;

import java.util.LinkedList;

import static javafx.scene.paint.Color.*;
import static javafx.scene.paint.Color.GREEN;

public class StartovniDomecek {

    private LinkedList<Figurka> figurky = new LinkedList<>();
    private int pocet;
    

    public StartovniDomecek(int pocet, BarvaFigurky barvaFigurky){
        for(int i=0;i<pocet;i++){
            figurky.addFirst(new Figurka(barvaFigurky));
        }
    }

    public Figurka nasadFigurku(){
        if(!jePrazdny()) return figurky.removeFirst();
        else return null;
    }

    public void vratFigurku(Figurka figurka){figurky.addFirst(figurka);}

    public boolean jePrazdny() {return figurky.isEmpty();}

    public int zbyvaFigurek() {
        if (jePrazdny()) {
            return 0;
        }
        return figurky.size();
    }
    public String toString() {



        LinkedList<Figurka> figurky = new LinkedList<>();
        int pocet = 4;

        StringBuilder sb = new StringBuilder();


        for (int i = 0; i < pocet;i++) {
            if (figurky.get(i) == null) {
                sb.append("0");
            }
            else {
                sb.append("1");
            }
            sb.append("|");
        }

        return sb.toString();



    }
    public static int[] souradnice (int hrac) {

        int [] souradniceXY = new int[8];
        if (hrac == 0) {

            souradniceXY = new int[]{75, 75, 140, 140,  82, 142, 82, 142};

        }
        if (hrac == 1) {
            new BarvaFigurky(10, 40, BLUE);}
        if (hrac == 2) {
            new BarvaFigurky(20, 40, YELLOW);
        }
        if (hrac == 3) {
            new BarvaFigurky(30, 40, GREEN);
        }



    return souradniceXY;
    }
}