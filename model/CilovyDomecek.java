package model;

import java.util.ArrayList;
import java.util.List;

public class CilovyDomecek {

    private List<Figurka> figurky;
    private BarvaFigurky barvaFigurky;
    private int pocet = 4;





    public CilovyDomecek(int pocet, BarvaFigurky barvaFigurky) {

        figurky = new ArrayList<>(pocet);
        this.pocet = pocet;
        this.barvaFigurky = barvaFigurky;
        for (int i = 0; i < pocet; i++) {
            figurky.add(i, null);
        }
    }

    public boolean jeVolno(int pozice) {
        if (figurky.get(pozice) == null) return true;

        return false;
    }

    public boolean jePlno(int pozice) {
        if (figurky.contains(null)) {
            return false;
        }
        return true;
    }


    public void setFigurka(int pozice, Figurka figurka) {

        if (jeVolno(pozice)) {
            figurky.set(pozice, figurka);
        }

    }


    public List<Figurka> getFigurkyKPohybu(){
        List<Figurka> figurkaList = new ArrayList<>();
        for (Figurka f: figurky){
            for (int i = figurky.indexOf(f); i < pocet; i++) {
                if (figurky.get(i)==null) figurkaList.add(f);
            }
        }
        return figurkaList;
        }

    public String toString() {
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
    }



