// Copyright (c) barbute
// Open Source Software
// ---
// Taken from:
// https://github.com/wpilibsuite/allwpilib/blob/main/wpilibj/src/main/java/edu/wpi/first/wpilibj/event/EventLoop.java

package sallet.event;

import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.LinkedHashSet;

/**
 * A declarative way to bind a set of actions to a loop and execute them when the loop is polled.
 */
public final class EventLoop {
  private final Collection<Runnable> BINDINGS = new LinkedHashSet<>();
  private boolean isRunning;

  /** Default constructor. */
  public EventLoop() {}

  /**
   * Bind a new action to run when the loop is polled.
   *
   * @param action the action to run.
   */
  public void bind(Runnable action) {
    if (isRunning) {
      throw new ConcurrentModificationException("Cannot bind EventLoop while it is running");
    }
    BINDINGS.add(action);
  }

  /** Poll all bindings. */
  @SuppressWarnings("PMD.UnusedAssignment")
  public void poll() {
    try {
      isRunning = true;
      BINDINGS.forEach(Runnable::run);
    } finally {
      isRunning = false;
    }
  }

  /** Clear all bindings. */
  public void clear() {
    if (isRunning) {
      throw new ConcurrentModificationException("Cannot clear EventLoop while it is running");
    }
    BINDINGS.clear();
  }
}