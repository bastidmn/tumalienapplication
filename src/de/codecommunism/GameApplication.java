package de.codecommunism;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class GameApplication extends Application {
    private Controller controller = new Controller();

    private Dialog d = new Alert(Alert.AlertType.INFORMATION, "Lol");

    public static void startApp(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        //set scene and title
        Scene scene = new Scene(new Group(), 500, 250);
        stage.setTitle("JavaFX Buttons and TextFields - Get/Set Values");
        stage.setAlwaysOnTop(true);

        //instantiate TextFields
        TextField ipField = new TextField();

        //instantiate buttons
        Button setIpButton = new Button("Set");
        Button shootButton = new Button("Shoot");

        //instantiate gridpane, setVgap,Hgap,padding and add children
        GridPane grid = new GridPane();
        grid.setVgap(5);
        grid.setHgap(10);
        grid.setPadding(new Insets(5, 5, 5, 5));
        grid.add(new Label("Set Host Ip: "), 0, 0);
        grid.add(ipField, 1, 0);
        grid.add(setIpButton, 2, 0);
        grid.add(shootButton, 2, 1);

        //listen to mouse click events our our buttons
        setIpButton.setOnMouseClicked((MouseEvent event) -> {
            String ip = ipField.getText();
            ipField.clear();
            System.out.println("Set Ip: " + ip);
            controller.connect(ip);
            //set textfield value
        });
        shootButton.setOnMouseClicked((MouseEvent event) -> {
            System.out.println("Shoot");
            try {
                controller.client.sendShoot();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        //add gridpane to group
        Group root = (Group) scene.getRoot();
        root.getChildren().add(grid);
        //set scene and show stage
        stage.setScene(scene);
        stage.show();
    }
}
