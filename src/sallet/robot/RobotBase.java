// Copyright (c) barbute
// Open Source Software

package sallet.robot; 

// -------------------------------------------------------------------------------------------------

/**
 * A container for declaring a Robot Program. The RobotBase class is meant to be subclassed and used
 * as a class to declare subsystems, bindings, and periodic calls that will then be used in
 * different OpModes. Those OpModes will then be able to call methods from the subclassed container
 * and reduce code rewrite.
 */
public abstract class RobotBase {
  /** Ran once when ANY OpMode is initialized */
  public void generalInit() {}

  /** Ran when ANY OpMode periodic call is made */
  public void generalPeriodic() {}

  /** Ran once when a Teleoperated OpMode is initialized */
  public void teleopInit() {}

  /** Ran when a Teleoperated OpMode periodic call is made */
  public void teleopPeriodic() {}

  /** Ran once when an Autonomous OpMode is initialized */
  public void autonomousInit() {}

  /** Ran when an Autonomous OpMode periodic call is made */
  public void autonomousPeriodic() {}
}