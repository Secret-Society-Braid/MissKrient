module org.braid.society.secret.mfm {

  requires lombok;
  requires com.fasterxml.jackson.core;
  requires com.fasterxml.jackson.databind;
  requires com.fasterxml.jackson.annotation;
  requires com.google.common;
  requires jakarta.annotation;
  requires org.slf4j;

  opens org.braid.society.secret.mfm.api to
    com.fasterxml.jackson.databind,
    org.braid.society.secret.misskrient;
}
