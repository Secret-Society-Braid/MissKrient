module org.braid.society.secret.misskrient {
  requires javafx.controls;
  requires javafx.fxml;

  requires java.sql;
  requires kotlin.stdlib;
  requires com.google.common;
  requires org.slf4j;
  requires java.desktop;
  requires misskey4j;
  requires okhttp3;
  requires com.fasterxml.jackson.core;
  requires com.fasterxml.jackson.databind;
  requires com.fasterxml.jackson.annotation;
  requires lombok;
  requires jakarta.annotation;

  opens org.braid.society.secret.misskrient;
  opens org.braid.society.secret.misskrient.internal.account;
  opens org.braid.society.secret.misskrient.controller;
  exports org.braid.society.secret.misskrient.controller to javafx.fxml;
  exports org.braid.society.secret.misskrient.internal.account to com.fasterxml.jackson.databind;
}
