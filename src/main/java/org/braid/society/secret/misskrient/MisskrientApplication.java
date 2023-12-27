package org.braid.society.secret.misskrient;

import java.io.IOException;
import java.util.Objects;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MisskrientApplication extends Application {

  public static void main(String[] args) {
    launch();
  }

  @Override
  public void start(Stage stage) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(
      MisskrientApplication.class.getResource("main-view.fxml"));
    Scene scene = new Scene(fxmlLoader.load(), 1080, 720);
    scene.getStylesheets().add(
      Objects.requireNonNull(MisskrientApplication.class.getResource("main.css")).toExternalForm());
    stage.setTitle("Hello!");
    stage.setScene(scene);
    stage.show();
  }
}