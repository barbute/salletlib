// Copyright (c) barbute
// Open Source Software
// ---
// Taken from:
// https://github.com/wpilibsuite/allwpilib/blob/main/wpiutil/src/main/java/edu/wpi/first/util/ErrorMessages.java

package sallet.util.error;

import static java.util.Objects.requireNonNull;

/** Utility class for common error messages. */
public final class ErrorMessages {
  /** Utility class, so constructor is private. */
  private ErrorMessages() {
    throw new UnsupportedOperationException("This is a utility class!");
  }

  /**
   * Requires that a parameter of a method not be null; prints an error message with helpful
   * debugging instructions if the parameter is null.
   *
   * @param <T> Type of object.
   * @param obj The parameter that must not be null.
   * @param paramName The name of the parameter.
   * @param methodName The name of the method.
   * @return The object parameter confirmed not to be null.
   */
  public static <T> T requireNonNullParam(T obj, String paramName, String methodName) {
    return requireNonNull(
        obj,
        "Parameter "
            + paramName
            + " in method "
            + methodName
            + " was null when it"
            + " should not have been!  Check the stacktrace to find the responsible line of code - "
            + "usually, it is the first line of user-written code indicated in the stacktrace.  "
            + "Make sure all objects passed to the method in question were properly initialized -"
            + " note that this may not be obvious if it is being called under "
            + "dynamically-changing conditions!  Please do not seek additional technical assistance"
            + " without doing this first!");
  }
}
