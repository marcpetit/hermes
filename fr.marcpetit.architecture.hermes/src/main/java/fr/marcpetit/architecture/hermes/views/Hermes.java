package fr.marcpetit.architecture.hermes.views;

import java.io.IOException;
import java.net.URL;

import fr.marcpetit.architecture.hermes.controllers.Main;
import fr.marcpetit.architecture.hermes.model.Model;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Hermes extends Application {

	private Model model;
	private Scene scene;
	
    @Override
    public void start(Stage stage) {
    	model = new Model();
        stage.setScene(buildScene(stage));
        stage.setTitle("Hermes - ");
        stage.show();
    }

    //this variable needs to go in the Main class, outside of the start() method
    long lastRefreshTime = 0;

    private Scene buildScene(Stage stage) {
        try {        	
            final URL url = getClass().getResource("Main.fxml");
            final FXMLLoader fxmlLoader = new FXMLLoader(url);
            
            final BorderPane root = (BorderPane) fxmlLoader.load();
            scene = new Scene(root);
            Main main = (Main)fxmlLoader.getController();
            main.setModel(model);
            main.setStage(stage);
          } catch (IOException ex) {
            System.err.println("Error loading : " + ex);
          }
        return scene;
    }
    
    public static void main(String[] args) {
        launch();
    }

}
