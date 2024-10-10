// Copyright (c) barbute
// Open Source Software
// ---
// Taken from:
// https://github.com/wpilibsuite/allwpilib/blob/main/wpimath/src/main/java/edu/wpi/first/math/filter/Debouncer.java

package sallet.math.filters;

import sallet.math.universal.MathSharedStore;

/**
 * A simple debounce filter for boolean streams. Requires that the boolean change value from
 * baseline for a specified period of time before the filtered value changes.
 */
public class Debouncer {
  /** Type of debouncing to perform. */
  public enum DebounceType {
    /** Rising edge. */
    RISING,
    /** Falling edge. */
    FALLING,
    /** Both rising and falling edges. */
    BOTH
  }

  private final double DEBOUNCE_TIME_SEC;
  private final DebounceType DEBOUNCE_TYPE;
  private boolean BASELINE;

  private double PREV_TIME_SEC;

  /**
   * Creates a new Debouncer.
   *
   * @param debounceTime The number of seconds the value must change from baseline for the filtered
   *     value to change.
   * @param type Which type of state change the debouncing will be performed on.
   */
  public Debouncer(double debounceTime, DebounceType type) {
    DEBOUNCE_TIME_SEC = debounceTime;
    DEBOUNCE_TYPE = type;

    resetTimer();

    BASELINE =
        switch (DEBOUNCE_TYPE) {
          case BOTH, RISING -> false;
          case FALLING -> true;
        };
  }

  /**
   * Creates a new Debouncer. Baseline value defaulted to "false."
   *
   * @param debounceTime The number of seconds the value must change from baseline for the filtered
   *     value to change.
   */
  public Debouncer(double debounceTime) {
    this(debounceTime, DebounceType.RISING);
  }

  private void resetTimer() {
    PREV_TIME_SEC = MathSharedStore.getTimestampSec();
  }

  private boolean hasElapsed() {
    return MathSharedStore.getTimestampSec() - PREV_TIME_SEC >= DEBOUNCE_TIME_SEC;
  }

  /**
   * Applies the debouncer to the input stream.
   *
   * @param input The current value of the input stream.
   * @return The debounced value of the input stream.
   */
  public boolean calculate(boolean input) {
    if (input == BASELINE) {
      resetTimer();
    }

    if (hasElapsed()) {
      if (DEBOUNCE_TYPE == DebounceType.BOTH) {
        BASELINE = input;
        resetTimer();
      }
      return input;
    } else {
      return BASELINE;
    }
  }
}
