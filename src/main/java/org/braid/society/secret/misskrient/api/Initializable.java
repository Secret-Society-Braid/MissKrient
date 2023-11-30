package org.braid.society.secret.misskrient.api;

/**
 * This interface is used to mark a class that must be initialized before use.
 */
public interface Initializable {

  /**
   * The method that will be called to initialize the class.
   * <p>
   * this method will be called by main opening process.
   */
  void initialize();
}
