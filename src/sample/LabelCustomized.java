package sample;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class LabelCustomized extends Label {

    //Código para obtener la hora, minuto y segundo actual de la pc
    //Donde calendario es la variable que usaremos para obtener cada instancia
    Calendar calendario = new GregorianCalendar();

    int horas = calendario.get(Calendar.HOUR_OF_DAY);
    int minutos = calendario.get(Calendar.MINUTE);
    int segundos = calendario.get(Calendar.SECOND);

    final int[] contadorS = {segundos};
    final int[] contadorM = {minutos};
    final int[] contadorH = {horas};
    private IntegerProperty pptnumClics;

    //Se crean los set y get

    public int getPptnumClics() {
        return pptnumClics.get();
    }

    public IntegerProperty pptnumClicsProperty() {
        return pptnumClics;
    }

    public void setPptnumClics(int pptnumClics) {
        this.pptnumClics.set(pptnumClics);
    }

    public LabelCustomized(String text) {
        super(text);

        pptnumClics = new SimpleIntegerProperty(0);

        //Rotar el botón 45 grados
        this.setRotate(-360);
        this.setTranslateX(230);
        this.setTranslateY(100);
        this.setScaleX(3);
        this.setScaleY(3);
        this.setBorder(new Border(new BorderStroke(Color.GREENYELLOW,
                BorderStrokeStyle.DOTTED, CornerRadii.EMPTY, BorderWidths.FULL)));

        DropShadow sombra = new DropShadow ();
        this.setEffect (sombra);

        //Código que implementa un Task y dentro de un Thread
        //Se ejecuta el programa que simula ser un reloj tomando un descanso cada 1 segundo
        Task tarea= new Task() {
            @Override
            protected Object call() throws Exception {
                while(contadorS[0]<=60) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            Reloj(contadorH,contadorM,contadorS);
                        }
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    contadorS[0]++;
                    if(contadorS[0]>=60){
                        contadorS[0]-=59;
                        contadorM[0]++;
                    }
                    if(contadorM[0]>=60){
                        contadorM[0]-=60;
                        contadorH[0]++;
                    }
                    if(contadorH[0]>24){
                        contadorH[0]-=24;
                    }
                }
                return null;
            }
        };
        Thread hilo = new Thread(tarea);
        hilo.setDaemon(true);
        hilo.start();
    }
    public void contarClic(){
        contadorS[0]+=1;
        this.setPptnumClics(this.getPptnumClics()+1);
    }

    public void Reloj(int[]Hora, int[] Minuto, int[] Segundo){
        this.setText("Hora:"+Hora[0]+"   Minuto:"+Minuto[0]+"   Segundo:"+Segundo[0]);
    }


    //Método para detectar eventOnAction, contar el clic
    //y ejecutar lo que el usuario desea desde su GUI
    public void setOnActionClic(EventHandler<ActionEvent> evt){
        this.contarClic();

        //Ejecutar el evento del usuario

        //Clase Skin;

    }
}
