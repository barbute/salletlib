// Copyright (c) barbute
// Open Source Software

package sallet.command;

import java.util.HashSet;
import java.util.Set;

/** 
 * A command is a complete action that can be performed by the robot
 * 
 * All commands are run synchronously, without multi-threading
 */
public abstract class Command {
  // What subsystems this command makes use of
  private final Set<Subsystem> REQUIREMENTS = new HashSet<>();

  /** Initial routine of the command - called once at start */
  public void initialize() {}

  /** Main part of the command - called repeatedly while the command is scheduled*/
  public void execute() {}

  /** 
   * Action the command takes when it finishes - called once at end 
   * 
   * @param interrupted If the command properly finished or was instructed to end
   */
  public void end(boolean interrupted) {}

  /**
   * @return Whether the command has finished
   */
  public boolean isFinished() {
    return false;
  }

  /**
   * @return The required subsystems for this command
   */
  public Set<Subsystem> getRequirements() {
    return REQUIREMENTS;
  }

  /**
   * Add the required subsystems to this command. The scheduler will prevent two commands that 
   * require the same subsystem to be scheduled at the same time
   * 
   * @param requiredSubsystems The subsystems to allocate for this command
   */
  public final void addRequirements(Subsystem... requiredSubsystems) {
    for (Subsystem subsystem : requiredSubsystems) {
      REQUIREMENTS.add(subsystem);
    }
  }

  /** Schedules this command to be run */
  public void schedule() {
    // CS implementation
  }

  /** 
   * Invokes the {@link #end(boolean) end(true)} method. Will be canceled regardless of the
   * current end behavior
   */
  public void cancel() {
    // CS implementation
  }

  /**
   * Note that this does not return if the command is currently in a composition, only if it
   * is being directly ran by the scheduler during the cycle this method is called.
   * 
   * @return If the command is scheduled to be run or is running
   */
  public boolean isScheduled() {
    // CS implementation
    return false;
  }

  /**
   * Checks if a given subsystem is required by this command
   * 
   * @param requirement The subsystem to lookup
   * @return If the passed subsystem is required by this command
   */
  public boolean hasRequirement(Subsystem requirement) {
    return getRequirements().contains(requirement);
  }

  /**
   * How a command will behave when another command with the same requirements is scheduled
   * to be ran
   * 
   * @return The interruption behavior of this command
   */
  public InterruptionBehavior getInterruptionBehavior() {
    return InterruptionBehavior.kCancelSelf;
  }

  /**
   * An enum describing the command's behavior when another command with a shared requirement is
   * scheduled.
   */
  public enum InterruptionBehavior {
    /**
     * This command ends, {@link #end(boolean) end(true)} is called, and the incoming command is
     * scheduled normally.
     *
     * <p>This is the default behavior.
     */
    kCancelSelf,
    /** This command continues, and the incoming command is not scheduled. */
    kCancelIncoming
  }
}
