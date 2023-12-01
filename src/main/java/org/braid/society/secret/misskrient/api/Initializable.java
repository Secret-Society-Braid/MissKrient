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

  /**
   * Set the initialized state of the class.
   * @param initialized the initialized state of the class.
   */
  void setInitialized(boolean initialized);

  /**
   * Get the initialized state of the class.
   * @return the initialized state of the class.
   */
  boolean isInitialized();
}
