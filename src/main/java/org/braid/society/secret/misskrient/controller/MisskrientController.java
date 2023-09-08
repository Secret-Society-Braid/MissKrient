package org.braid.society.secret.misskrient.controller;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.UUID;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javax.swing.JOptionPane;
import lombok.extern.slf4j.Slf4j;
import misskey4j.Misskey;
import misskey4j.MisskeyFactory;
import misskey4j.api.request.notes.NotesTimelineRequest;
import misskey4j.api.response.notes.NotesTimelineResponse;
import misskey4j.entity.contant.Scope;
import misskey4j.entity.share.Response;
import org.braid.society.secret.misskrient.api.account.AccountInfo;
import org.braid.society.secret.misskrient.api.auth.Auth;
import org.braid.society.secret.misskrient.api.auth.AuthUrlConfig;
import org.braid.society.secret.misskrient.api.database.Repository;
import org.braid.society.secret.misskrient.internal.account.AccountRepository;
import org.braid.society.secret.misskrient.internal.auth.misskey.MiAuth;
import org.braid.society.secret.misskrient.internal.auth.misskey.MiAuthUrlConfig;

@Slf4j
public class MisskrientController {

  @FXML
  private Label welcomeText;

  @FXML
  protected void onHelloButtonClick() {
    log.info("onHelloButtonClick");
    welcomeText.setText("Welcome to JavaFX Application!");

    Repository<AccountInfo> r = new AccountRepository();
    AccountInfo i = r.get(0);

    Misskey m = MisskeyFactory.getInstanceWithOwnedAccessToken(i.getHost(), i.getAccessToken());
    Response<NotesTimelineResponse[]> res = m
      .notes()
      .timeline(
        NotesTimelineRequest.builder()
          .limit(100L)
          .includeMyRenotes(false)
          .includeLocalRenotes(false)
          .build());
    for (NotesTimelineResponse response : res.get()) {
      log.info(response.toString());
    }
  }

  @FXML
  protected void onAuthButtonClick() {
    log.info("Start auth");
    AuthUrlConfig config = MiAuthUrlConfig.newBuilder(UUID.randomUUID().toString()).hostname("misskey.io").scopes(
      Scope.ALL);
    Repository<AccountInfo> r = new AccountRepository();
    Auth auth = new MiAuth(config, r);
    try {
      Desktop.getDesktop().browse(new URL(auth.constructAuthUrl()).toURI());
    } catch (IOException | URISyntaxException e) {
      log.error("Failed to process URLs", e);
    }
    JOptionPane.showConfirmDialog(null, "Please press OK after authentication.", "Misskrient", JOptionPane.OK_CANCEL_OPTION);
    auth.authenticate();
    JOptionPane.showConfirmDialog(null, "Authentication completed.", "Misskrient", JOptionPane.OK_CANCEL_OPTION);
  }
}