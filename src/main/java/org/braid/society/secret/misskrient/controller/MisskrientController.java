package org.braid.society.secret.misskrient.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MisskrientController {

  @FXML
  protected Button funcButton;

  @FXML
  protected void onColumnSwitchButtonPressed(ActionEvent e) {
    log.info("onColumnSwitchButtonPressed");
    log.info("Event detail: {}", e);
    log.info("Event source: {}", e.getSource());

    if (e.getSource() instanceof Button b) {
      log.info("Button text: {}", b.getText());
    } else {
      throw new ClassCastException(
        "Failed to recognize event source due to improper action event property.");
    }

    // TODO: implement column switching by button text
  }

  @FXML
  protected void onFuncButtonPressed(ActionEvent e) {
    log.info("onFuncButtonPressed");
    log.info("Event detail: {}", e);
    log.info("Event source: {}", e.getSource());
  }

}