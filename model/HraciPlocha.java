package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static javafx.scene.paint.Color.GREEN;

public class HraciPlocha {
    private Map<BarvaFigurky, StartovniDomecek> startovniDomecekmap = new HashMap<>();
    private Map<BarvaFigurky, CilovyDomecek> cilovyDomecekMap = new HashMap<>();
    private Map<BarvaFigurky, Figurka> nasazeneFigurky = new HashMap<>();
    private List<Figurka> hraciPole = new ArrayList<>();

    public HraciPlocha(int pocetHracu, int pocetFigurek, int pocetMeziPoli) {



//method body





        for (int i = 0; i < pocetHracu; i++) {
            BarvaFigurky barvaFigurky = new BarvaFigurky(i*pocetMeziPoli, pocetHracu*pocetMeziPoli, GREEN);
            StartovniDomecek startovniDomecek = new StartovniDomecek(pocetFigurek, barvaFigurky);
            startovniDomecekmap.put(barvaFigurky, startovniDomecek);

            CilovyDomecek cilovyDomecek = new CilovyDomecek(pocetFigurek, barvaFigurky);
            cilovyDomecekMap.put(barvaFigurky, cilovyDomecek);
        }
        for (int x = 0; x < pocetMeziPoli * pocetHracu; x++) {
            hraciPole.add(x, null);
        }

    }
        public void nasad (BarvaFigurky barvaFigurky) {}
        public int nasad (Figurka figurka) {
           // int nasazeni= figurka.getBarvaFigurky().getStart();
          //  hraciPole.set(nasazeni, figurka);
            int ok = 1;
            return ok;
        }


        public void posunFigurku(Figurka figurka, int pocetPoli){
            int staraPozice = hraciPole.indexOf(figurka);
            int novaPozice = staraPozice + pocetPoli;
            hraciPole.set(novaPozice, figurka);
             
            hraciPole.set(staraPozice,null);
            if(hraciPole.get(novaPozice)!=null){
                startovniDomecekmap.get(hraciPole.get(novaPozice).getBarvaFigurky()).vratFigurku(hraciPole.get(novaPozice));
            }
        }
        public void posunFigurku(int odkud, int pocetpoli){

        }

        public List<Figurka> vratMozneFigurky(BarvaFigurky barvaFigurky, int pocetpoli) {
        List<Figurka> figurky = new ArrayList<>();
        for(Figurka f : hraciPole) {
            if (f.getBarvaFigurky()==barvaFigurky&&(muzuPohyb(f,pocetpoli))) figurky. add(f) ;
        }
        return null;
        }
        
        private boolean muzuPohyb(Figurka f, int pocetpoli) {
        boolean vysledek = false;
        Figurka figurkaPredeMnou = hraciPole.get(hraciPole.indexOf(f)+pocetpoli);
        if (figurkaPredeMnou == null) vysledek = true;
        else if  (figurkaPredeMnou.getBarvaFigurky()!=f.getBarvaFigurky()) vysledek = true;
        return vysledek;




    }




















}
