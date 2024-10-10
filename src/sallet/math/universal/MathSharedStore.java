// Copyright (c) barbute
// Open Source Software

package sallet.math.universal;

import java.util.function.DoubleSupplier;

/**
 * Class that stores useful data needed for mathematic calculations
 */
public class MathSharedStore {
  private static DoubleSupplier timestampSupplier = null;

  /**
   * Binds the time supplier to the internal timstamp supplier
   * @param timeSupplier The time supplier object from the internal timer
   */
  public static void bindTimestampSupplier(DoubleSupplier timeSupplier) {
    timestampSupplier = timeSupplier;
  }

  /**
   * @return The internal controller's timstamp in seconds
   */
  public static double getTimestampSec() {
    if (timestampSupplier != null) {
      return timestampSupplier.getAsDouble();
    } else {
      return 0.0;
    }
  }
}
