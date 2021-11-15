package model;

import javafx.scene.paint.Color;

import static javafx.scene.paint.Color.*;

public class BarvaFigurky {

    static int start;
    int cil;



    public BarvaFigurky(int start, int pocetPoli, Color color){
        this.start = start;
        int cil = start - 1;
        if (start==0) cil = pocetPoli - 1;



    }






    public static int getStart(int hrac){

        if (hrac == 0) {

            new BarvaFigurky(0, 40, RED);
        }
        if (hrac == 1) {
            new BarvaFigurky(10, 40, BLUE);}
        if (hrac == 2) {
            new BarvaFigurky(20, 40, YELLOW);
        }
        if (hrac == 3) {
            new BarvaFigurky(30, 40, GREEN);
        }

        return start;
    }
    public static Color getBarva(int hrac) {
        Color color = null;
        if (hrac == 0) {

            color = Color.RED;
        }
        if (hrac == 1) {
            color = Color.BLUE;
        }
            if (hrac == 2) {
                color = Color.YELLOW;
            }
            if (hrac == 3) {
                color = Color.GREEN;
            }

        return color;
    }


    public int getCil(){
        return cil;
    }
}
