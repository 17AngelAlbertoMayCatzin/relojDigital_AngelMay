package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        VBox contenedorPrincipal = new VBox();
        LabelCustomized myLabel = new LabelCustomized("");

        //Poner un componente que muestre el conteo de clics
        Label lblClics = new Label();


        VBox vbox = new VBox();
        vbox.getChildren().addAll(lblClics,myLabel);
        contenedorPrincipal.getChildren().add(vbox);

        myLabel.setOnActionClic(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                myLabel.contarClic();
                lblClics.setText("Clics "+myLabel.getPptnumClics());
            }
        });



        //Vincular una property del componente con la property pptNnumClics del botón

        primaryStage.setTitle("Reloj en un Label");
        primaryStage.setScene(new Scene(contenedorPrincipal, 600, 300));
        primaryStage.setResizable(false);
        primaryStage.show();
    }





    public static void main(String[] args) {
        launch(args);
    }
}
