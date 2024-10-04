// Copyright (c) barbute
// Open Source Software

package sallet.command;

/**
 * -------------------------------------------------------------------------------------------------
 * The CommandScheduler is responsible for running {@link Command}s. 
 */
public class CommandScheduler {
  /** The scheduler singleton instance */
  private static CommandScheduler instance;

  /**
   * @return The instance of the CommandScheduler
   */
  public static synchronized CommandScheduler getInstance() {
    if (instance == null) {
      instance = new CommandScheduler();
    }
    return instance;
  }
}
