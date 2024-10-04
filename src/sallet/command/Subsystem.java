// Copyright (c) barbute
// Open Source Software

package sallet.command;

/**
 * A robot subsystem - a 'subsystem' is a class that encapsulate's low-level hardware objects and
 * allows the programmer to create methods for {@link Command}s to use. This is useful because then
 * commands have a way of "reserving" resources from a given subsystem when that command is running
 * and prevents commands from "fighting" over resources.
 * 
 * Note that a subsystem automatically registers itself with the {@link CommandScheduler} so that its
 * {@link #periodic} method can be called.
 */
public abstract class Subsystem {
  public Subsystem() {
    // Add CommandScheduler implementation here
  }

  /**
   * This method is called periodically by the {@link CommandScheduler}.
   */
  public void periodic() {}

  /**
   * @return The name of this subsystem
   */
  public String getName() {
    return this.getClass().getSimpleName();
  }

  /**
   * <p>Sets the default {@link Command} of the subsystem. When another command is not using this 
   * subsystem, this command will automatically be scheduled to use this subsystem's resources 
   * until another command requires this subsystem.
   */
  public final void setDefaultCommand() {
    // Add CommandScheduler implementation here
  }

  /**
   * <p>Removes the default command for this subsystem.
   */
  public final void removeDefaultCommand() {
    // Add CommandScheduler implementation here
  }

  /**
   * @return The currently set default command for this subsystem
   */
  public final Command getDefaultCommand() {
    // Add CommandScheduler implementation here
    return null;
  }

  /**
   * @return The command currently using this subsystem
   */
  public final Command getCurrentCommand() {
    // Add CommandScheduler implementation here
    return null;
  }
}
