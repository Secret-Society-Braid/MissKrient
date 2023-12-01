package org.braid.society.secret.misskrient.internal.exceptions;

public class CannotKeepUpException extends RuntimeException {

  public CannotKeepUpException() {
    super("Cannot keep up! The app will be terminated!");
  }
}
