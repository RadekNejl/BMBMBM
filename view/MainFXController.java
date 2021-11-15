
package view;

import cz.gjkt.zlomky.Kostka;
import model.BublinkoveRazeni;
import model.HraciPole;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.chart.NumberAxis;
import javafx.scene.layout.AnchorPane;
import model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import model.Ovál;

import javafx.fxml.FXMLLoader;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.Stage;
import javafx.scene.effect.Glow;
import javafx.scene.shape.Path;

import javafx.scene.input.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;

import java.lang.reflect.Method;
import java.util.*;

import javafx.scene.effect.Glow;

import static javafx.fxml.FXMLLoader.load;


public class MainFXController extends Application {



    @FXML
    Canvas platno;
    GridPane tabulka;
    @FXML  AnchorPane koleckoplatno;
    @FXML
    AnchorPane velkyan;
    Circle kolicko;
    private ImageView imageView;
    private BarvaFigurky barvaFigurky;
    private Color barvaFig;
    private GridPane grid;
    private Object CilovyDomecek;
    LinkedList<Circle>  Kolecka = new LinkedList<>();
    LinkedList<Ovál>  Ovály1 = new LinkedList<>();
    LinkedList<Ovál>  Ovály2 = new LinkedList<>();
    LinkedList<Ovál>  Ovály3 = new LinkedList<>();
    private int pozice[] = new int[16];
    Color[] Barvy = {Color.YELLOW, Color.GREEN, Color.RED, Color.BLUE};
    int [] hodnota = new int[1];
    double X;
    double Y;



    public void initaliaze() throws IOException {


        vypln();
        dostanFigurkydoHry();







    }

    public void dostanFigurkydoHry()  {



            for (int j = 0; j < 4; j++) {
                Color barva = Barvy[j];
                for (int k = 0; k < 16; k++) {

                    if (k % 4 == 0)   {
                        barva = Barvy[j++];
                    }

                    Circle kolecko = new Circle();
                    kolecko.setRadius(20);
                    kolecko.setLayoutX(souradniceDom(j,k)[0]);
                    kolecko.setLayoutY(souradniceDom(j,k)[1]);
                    kolecko.setOnMouseEntered(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            kolecko.setCursor(Cursor.CLOSED_HAND);
                            kolecko.setRadius(30);




                            }



                    });
                    kolecko.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {


                            X = (nejblizsiDomX(mouseEvent.getSceneX()));
                            String textCisla = Double.toString(X);

                            Y = (nejblizsiY(mouseEvent.getSceneY()));
                            String textCisla2 = Double.toString(Y);

                            platno.getGraphicsContext2D().setFill(Color.LIGHTGRAY);
                            platno.getGraphicsContext2D().fillRect(276,302,80,80);
                            platno.getGraphicsContext2D().setFont(Font.font(40));
                            platno.getGraphicsContext2D().setFill(Color.BLACK);
                            platno.getGraphicsContext2D().fillText(textCisla, 276, 302);

                            platno.getGraphicsContext2D().setFill(Color.LIGHTGRAY);
                            platno.getGraphicsContext2D().fillRect(306,352,80,80);
                            platno.getGraphicsContext2D().setFont(Font.font(40));
                            platno.getGraphicsContext2D().setFill(Color.BLACK);
                            platno.getGraphicsContext2D().fillText(textCisla2, 306, 352);


                            try {
                                NasadFigurku();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }








                    });
                    kolecko.setOnMouseExited(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            kolecko.setRadius(20);
                        }
                    });
                    kolecko.setFill(barva);





                    Kolecka.add(kolecko);
                }
            }





            for (int i = 0; i < Kolecka.size(); i++) {

                koleckoplatno.getChildren().add(Kolecka.get(i));
            }
        }

        public void vyberFigurky()  {
            Kolecka.get(0).setOnMouseClicked(event -> {
                try {
                    NasadFigurku();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

        }

        public void NasadFigurku() throws IOException {


            int[] souradniceX = {85,85,150,150,85,85,150,150,385,385,450,450,385,385,450,450};
            int[] souradniceY = {82,142,82,142,382,442,382,442,82,142,82,142,382,442,382,442};


        if (hodnota[0] == 6) {
            Kolecka.get(indexOf(X, Y,souradniceX, souradniceY )).setLayoutX(souradnice(pozice[0])[0]);
            Kolecka.get(indexOf(X, Y,souradniceX, souradniceY )).setLayoutY(souradnice(pozice[0])[1]);
        }


        }






       public void PosunFigurku() {
           int[] souradniceX = {85,85,150,150,85,85,150,150,385,385,450,450,385,385,450,450};

        pozice[0] = pozice[0] + hodnota[0];

        Kolecka.get(Kolecka.indexOf(135)).setLayoutX(souradnice(pozice[0])[0]);
        Kolecka.get(Kolecka.indexOf(Y)).setLayoutY(souradnice(pozice[0])[1]);


       }






    public MainFXController() throws FileNotFoundException, NullPointerException {
        /*Circle kolecko=new Circle(300,300,100, Color.BLACK);

    koleckoplatno.getChildren().add(kolecko);*/




    }

    @Override
    public void start(Stage window) throws Exception {

    }


    public void zmenObrazovku(ActionEvent event) throws IOException {



        Parent menuParent = FXMLLoader.load(getClass().getResource("/view/MainFX.fxml"));


        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene menuScéna = new Scene (menuParent);

        window.setScene(menuScéna);
        window.show();








    }
    public int indexOf(double cilX, double cilY, int[] souradniceDomX, int[] souradniceDomY) {

        int i = 0;
        int vysledek = 0;

        while ((cilX == souradniceDomX[i]) & (cilY == souradniceDomY[i])) {

            if ((cilX == souradniceDomX[i]) & (cilY == souradniceDomY[i])) {
                vysledek = i;
            } else i++;


        }
        return vysledek;
    }



    public void zpatkyDoMenu(ActionEvent event) throws IOException {
        Parent menuParent = load(getClass().getResource("/view/MenuFXML.fxml"));
        Scene menuScéna = new Scene (menuParent);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(menuScéna);
        window.show();




    }




    public long nejblizsiX(double cil) {
        int rozdil = 30;

        int[] souradniceX = new int[]{35, 85, 135, 185, 235, 235, 235, 235, 235, 285, 335
                , 335, 335, 335, 335, 385, 435, 485, 535, 535, 535, 485, 435, 385,
                335, 335, 335, 335, 335, 285, 235, 235, 235, 235, 235, 185, 135, 85, 35, 35};

            BublinkoveRazeni br = new BublinkoveRazeni();
            int[] serazeneX = br.bubbleSort(souradniceX);

            if (serazeneX.length == 1) {
                return serazeneX[0];
            }    // trivial case
            if (cil < serazeneX[0]) {
                return serazeneX[0];
            } // lower boundary
            if (cil > serazeneX[serazeneX.length - 1]) {
                return serazeneX[serazeneX.length - 1];
            } // upper boundary
            int pos = Arrays.binarySearch(serazeneX, (int) cil);
            if (pos >= 0) {
                return serazeneX[pos];
            } // we found an exact match
            // we didn't find an exact match, now we have two candidates: insertion point and insertion point-1 (we excluded the trivial case before)
            // pos = -ip-1 | +ip -pos => ip = -pos-1
            int ip = -pos - 1;
            long nejblizsi;
            if (serazeneX[ip] - cil < cil - serazeneX[ip - 1]) {
                nejblizsi = serazeneX[ip];
            } // < can be <= if smaller value is preferred
            else {
                nejblizsi = serazeneX[ip - 1];
            }
            return nejblizsi;
        }
    public int nejblizsiDomX( double cil) {
        int rozdil = 30;

        int[] souradniceX = {85,85,150,150,85,85,150,150,385,385,450,450,385,385,450,450};


        BublinkoveRazeni br = new BublinkoveRazeni();
        int[] serazeneX = br.bubbleSort(souradniceX);

        if (serazeneX.length == 1) {
            return serazeneX[0];
        }    // trivial case
        if (cil < serazeneX[0]) {
            return serazeneX[0];
        } // lower boundary
        if (cil > serazeneX[serazeneX.length - 1]) {
            return serazeneX[serazeneX.length - 1];
        } // upper boundary
        int pos = Arrays.binarySearch(serazeneX, (int) cil);
        if (pos >= 0) {
            return serazeneX[pos];
        } // we found an exact match
        // we didn't find an exact match, now we have two candidates: insertion point and insertion point-1 (we excluded the trivial case before)
        // pos = -ip-1 | +ip -pos => ip = -pos-1
        int ip = -pos - 1;
        int nejblizsi;
        if (serazeneX[ip] - cil < cil - serazeneX[ip - 1]) {
            nejblizsi = serazeneX[ip];
        } // < can be <= if smaller value is preferred
        else {
            nejblizsi = serazeneX[ip - 1];
        }

        int ziskejIndex = Arrays.asList(souradniceX).indexOf(nejblizsi);
        return ziskejIndex;
    }
    public long nejblizsiY(double cil) {
        int rozdil = 30;

        int[] souradniceY = {240, 240, 240, 240, 240, 190, 140, 90, 40, 40, 40,
                90, 140, 190, 240, 240, 240, 240, 240, 290, 340, 340, 340, 340, 340, 390, 440,
                490, 540, 540, 540, 490, 440, 390, 340, 340, 340, 340, 340, 290};

        BublinkoveRazeni br = new BublinkoveRazeni();
        int[] serazeneY = br.bubbleSort(souradniceY);

        if (serazeneY.length == 1) {
            return serazeneY[0];
        }    // trivial case
        if (cil < serazeneY[0]) {
            return serazeneY[0];
        } // lower boundary
        if (cil > serazeneY[serazeneY.length - 1]) {
            return serazeneY[serazeneY.length - 1];
        } // upper boundary
        int pos = Arrays.binarySearch(serazeneY, (int) cil);
        if (pos >= 0) {
            return serazeneY[pos];
        } // we found an exact match
        // we didn't find an exact match, now we have two candidates: insertion point and insertion point-1 (we excluded the trivial case before)
        // pos = -ip-1 | +ip -pos => ip = -pos-1
        int ip = -pos - 1;
        long nejblizsi;
        if (serazeneY[ip] - cil < cil - serazeneY[ip - 1]) {
            nejblizsi = serazeneY[ip];
        } // < can be <= if smaller value is preferred
        else {
            nejblizsi = serazeneY[ip - 1];
        }
        return nejblizsi;
    }











    public void vypniHru() {
        //System.exit(0);

        platno.getGraphicsContext2D().setFill(Color.BLACK);
        platno.getGraphicsContext2D().fillOval(220,20,20,20);
    }

    List <Integer> cisla = new ArrayList<Integer>();

    public int hod() throws IOException {

        int cislo = Kostka.hodCislo();
        hodnota[0] = cislo;

    String textCisla = Integer.toString(hodnota[0]);

    platno.getGraphicsContext2D().setFill(Color.LIGHTGRAY);
    platno.getGraphicsContext2D().fillRect(263,258,40,40);
    platno.getGraphicsContext2D().setFont(Font.font(40));
    platno.getGraphicsContext2D().setFill(Color.BLACK);
    platno.getGraphicsContext2D().fillText(textCisla, 276, 302);

    return hodnota[0];


/*
        FileInputStream CervenyDomecek = new FileInputStream("F:\\Obrázky\\Clobrdo\\CervenyDomecek.png");
        Image Cerveny = new Image(CervenyDomecek);

        platno.getGraphicsContext2D().setFill(Color.LIGHTGRAY);
        platno.getGraphicsContext2D().fillRect(0,0,800 ,600);
        vypln();



             Random random = new Random();
             int a = 1;
             a = random.nextInt(6) + 1;
        cisla.add(a);


            int sum = 0;
            for (int i = 0; i < cisla.size(); i++) {
                sum =+ i;
            }

            //hraciPlocha.posunFigurku(220+(1*sum),220+(50*sum));
            System.out.print(hraciPlocha.toString());
        platno.getGraphicsContext2D().drawImage(Cerveny,(220+(sum*50)), 25);






        String b = Integer.toString(a);
platno.getGraphicsContext2D().setFill(Color.BLACK);

*/









    }


    public int[] souradnice(int a) {


        int[] souradniceX = {35, 85, 135, 185, 235, 235, 235, 235, 235, 285, 335
                , 335, 335, 335, 335, 385, 435, 485, 535, 535, 535, 485, 435, 385,
                335, 335, 335, 335, 335, 285, 235, 235, 235, 235, 235, 185, 135, 85, 35, 35};

        int[] souradniceY = {240, 240, 240, 240, 240, 190, 140, 90, 40, 40, 40,
                90, 140, 190, 240, 240, 240, 240, 240, 290, 340, 340, 340, 340, 340, 390, 440,
                490, 540, 540, 540, 490, 440, 390, 340, 340, 340, 340, 340, 290};

        int[] souradnice = new int[2];
        souradnice[0] = souradniceX[a];
        souradnice[1] = souradniceY[a];

        return souradnice;



    }
    public int[] souradniceDom(int hrac, int a) {



        int[] souradniceX = {85,85,150,150,85,85,150,150,385,385,450,450,385,385,450,450};
        int[] souradniceY = {82,142,82,142,382,442,382,442,82,142,82,142,382,442,382,442};


        int[] souradniceDom = new int[2];
        souradniceDom[0] = souradniceX[a];
        souradniceDom[1] = souradniceY[a];

        return souradniceDom;
    }




    public void vypln() throws FileNotFoundException {


        FileInputStream Policko = new FileInputStream("F:\\Obrázky\\Clobrdo\\Policko.png");
        Image policko = new Image(Policko);
        FileInputStream ModryDomecek = new FileInputStream("F:\\Obrázky\\Clobrdo\\ModryDomecek.png");
        Image Modry = new Image(ModryDomecek);
        FileInputStream ZlutyDomecek = new FileInputStream("F:\\Obrázky\\Clobrdo\\ZlutyDomecek.png");
        Image Zluty = new Image(ZlutyDomecek);
        FileInputStream ZelenyDomecek = new FileInputStream("F:\\Obrázky\\Clobrdo\\ZelenyDomecek.png");
        Image Zeleny = new Image(ZelenyDomecek);
        FileInputStream CervenyDomecek = new FileInputStream("F:\\Obrázky\\Clobrdo\\CervenyDomecek.png");
        Image Cerveny = new Image(CervenyDomecek);



            // Červená
        platno.getGraphicsContext2D().setFill(Color.RED);
        //platno.getGraphicsContext2D().fillOval(75, 82, 30, 30);
        platno.getGraphicsContext2D().drawImage(Cerveny,75, 82);
            //platno.getGraphicsContext2D().fillOval(75, 142, 30, 30);
        platno.getGraphicsContext2D().drawImage(Cerveny,75, 142);
            //platno.getGraphicsContext2D().fillOval(140, 82, 30, 30);
        platno.getGraphicsContext2D().drawImage(Cerveny,140, 82);
            //platno.getGraphicsContext2D().fillOval(140, 142, 30, 30);
        platno.getGraphicsContext2D().drawImage(Cerveny,140, 142);


        // Zelená
        platno.getGraphicsContext2D().setFill(Color.GREEN);
        //platno.getGraphicsContext2D().fillOval(75, 382, 30, 30);
        platno.getGraphicsContext2D().drawImage(Zeleny,75, 382);
        //platno.getGraphicsContext2D().fillOval(75, 442, 30, 30);
        platno.getGraphicsContext2D().drawImage(Zeleny,75, 442);
        //platno.getGraphicsContext2D().fillOval(140, 382, 30, 30);
        platno.getGraphicsContext2D().drawImage(Zeleny,140, 382);
        //platno.getGraphicsContext2D().fillOval(140, 442, 30, 30);
        platno.getGraphicsContext2D().drawImage(Zeleny,140, 442);
        platno.getGraphicsContext2D().setFill(Color.RED);

        // MODRÁ
        platno.getGraphicsContext2D().setFill(Color.BLUE);
        //platno.getGraphicsContext2D().fillOval(375, 82, 30, 30);
        platno.getGraphicsContext2D().drawImage(Modry,375, 82);

        //platno.getGraphicsContext2D().fillOval(375, 142, 30, 30);
        platno.getGraphicsContext2D().drawImage(Modry,375, 142);
        //platno.getGraphicsContext2D().fillOval(440, 82, 30, 30);
        platno.getGraphicsContext2D().drawImage(Modry,440, 82);

        //platno.getGraphicsContext2D().fillOval(440, 142, 30, 30);
        platno.getGraphicsContext2D().drawImage(Modry,440, 142);

        // žLUTÁ
        platno.getGraphicsContext2D().setFill(Color.YELLOW);
        //platno.getGraphicsContext2D().fillOval(375, 382, 30, 30);
        platno.getGraphicsContext2D().drawImage(Zluty,375, 382);
        //platno.getGraphicsContext2D().fillOval(375, 442, 30, 30);
        platno.getGraphicsContext2D().drawImage(Zluty,375, 442);
        //platno.getGraphicsContext2D().fillOval(440, 382, 30, 30);
        platno.getGraphicsContext2D().drawImage(Zluty,440, 382);
        //platno.getGraphicsContext2D().fillOval(440, 442, 30, 30);
        platno.getGraphicsContext2D().drawImage(Zluty,440, 442);
        platno.getGraphicsContext2D().setFill(Color.RED);



        for (int i = 200; i < 350; i += 50) {




        //vodorovné
            platno.getGraphicsContext2D().setFill(Color.BLACK);
            //platno.getGraphicsContext2D().fillOval(i+20, 25, 20, 20);
            platno.getGraphicsContext2D().drawImage(policko, i+20, 25);
            //platno.getGraphicsContext2D().fillOval(i+20, 520, 20, 20);
            platno.getGraphicsContext2D().drawImage(policko, i+20, 520);

            //[20,40,60,80,80,80,80,100,120,140,140,140,140,

        //Startovni policka

        platno.getGraphicsContext2D().drawImage(Cerveny, 20,222);
        platno.getGraphicsContext2D().drawImage(Modry, 320, 25);
            platno.getGraphicsContext2D().drawImage(Zeleny, 220, 520);
            platno.getGraphicsContext2D().drawImage(Zluty, 520, 322);
            for (int j = 20; j < 200; j += 50) {



                //platno.getGraphicsContext2D().fillOval(j, 222,20, 20);
                platno.getGraphicsContext2D().drawImage(policko, j, 222);
                //platno.getGraphicsContext2D().fillOval(j+350, 222,20, 20);
                platno.getGraphicsContext2D().drawImage(policko, j+350, 222);
                platno.getGraphicsContext2D().setFill(Color.BLACK);

                platno.getGraphicsContext2D().drawImage(policko, j, 322);
                //platno.getGraphicsContext2D().fillOval(j+350, 322,20, 20);
                platno.getGraphicsContext2D().drawImage(policko, j+350, 322);
                platno.getGraphicsContext2D().setFill(Color.BLACK);



            }

            }

        // svislé
        for (int i = 40; i < 200; i += 50) {

            //platno.getGraphicsContext2D().fillOval(220, i+30, 20, 20);
            platno.getGraphicsContext2D().drawImage(policko, 220, i+30);
            //platno.getGraphicsContext2D().fillOval(320, i+30, 20, 20);
            platno.getGraphicsContext2D().drawImage(policko, 320, i+30);
            //platno.getGraphicsContext2D().fillOval(220, i+283, 20, 20);
            platno.getGraphicsContext2D().drawImage(policko, 220, i+283);
            //platno.getGraphicsContext2D().fillOval(320, i+283, 20, 20);
            platno.getGraphicsContext2D().drawImage(policko, 320, i+283);
            platno.getGraphicsContext2D().setFill(Color.BLACK);
        }
        //platno.getGraphicsContext2D().fillOval(20, 272, 20, 20);
        platno.getGraphicsContext2D().drawImage(policko, 20, 272);

        //platno.getGraphicsContext2D().fillOval(521, 272, 20, 20);
        platno.getGraphicsContext2D().drawImage(policko, 521, 272);

       // cerveny domecek
        for (int c = 70; c < 270; c+= 50)
        platno.getGraphicsContext2D().drawImage(Cerveny,c ,272);

       // zluty domecek
        for (int z = 320; z < 500; z+= 50){
        platno.getGraphicsContext2D().drawImage(Zluty,z ,272);

        //modry domecek
        for (int m = 70; m < 230; m+=50)
            platno.getGraphicsContext2D().drawImage(Modry,270, m);
    }
        //zeleny domecek
        for (int ze = 323; ze < 500; ze += 50) {
            platno.getGraphicsContext2D().drawImage(Zeleny, 270, ze);
        }




}

public void djf() {
        for (int i = 0; i < 40; i++) {
            platno.getGraphicsContext2D().setFill(Color.PURPLE);
            platno.getGraphicsContext2D().fillRect(souradnice(i)[0],souradnice(i)[1], 40,40);
        }
}
    public void mousePressed(MouseEvent e)
    {


        // show the point where the user pressed the mouse
        System.out.println("mouse pressed at point:"
                + e.getX() + " " + e.getY());
    }



}




