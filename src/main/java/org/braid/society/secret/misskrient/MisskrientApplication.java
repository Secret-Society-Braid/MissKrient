package org.braid.society.secret.misskrient;

import java.io.IOException;
import java.util.List;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.braid.society.secret.misskrient.api.Initializable;
import org.braid.society.secret.misskrient.internal.auth.CredentialHelper;

public class MisskrientApplication extends Application {

  public static void main(String[] args) {
    launch();
  }

  @Override
  public void start(Stage stage) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(
      MisskrientApplication.class.getResource("hello-view.fxml"));
    Scene scene = new Scene(fxmlLoader.load(), 960, 640);
    stage.setTitle("Hello!");
    stage.setScene(scene);
    this.initializer(
        CredentialHelper.summon()
    );
    stage.show();
  }

  private void initializer(Initializable... instances) {
    List<Initializable> initializables = List.of(instances);
    initializables.forEach((i) -> {
      if(i.isInitialized()) {
        return;
      }
      i.initialize();
      i.setInitialized(true);
    });
  }
}