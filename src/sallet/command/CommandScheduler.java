// Copyright (c) barbute
// Open Source Software

package sallet.command;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import sallet.event.EventLoop;

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

  private static final Optional<Command> NO_INTERRUPTER = Optional.empty();

  @SuppressWarnings({ "unchecked", "rawtypes" })
  private static final Map<Command, Exception> COMPOSED_COMMANDS = new WeakHashMap();

  /** Set of commands scheduled to be run */
  private final Set<Command> SCHEDULED_COMMANDS = new LinkedHashSet<>();

  /** Map of subsystems and the commands that require them */
  private final Map<Subsystem, Command> REQUIREMENTS = new LinkedHashMap<>();

  /** Map of subsystems and their default commands */
  private final Map<Subsystem, Command> SUBSYSTEMS = new LinkedHashMap<>();

  private final EventLoop DEFAULT_BUTTON_LOOP = new EventLoop();
  // Set of currently registered buttons that will be polled every cycle
  private EventLoop activeButtonLoop = DEFAULT_BUTTON_LOOP;

  private boolean disabled;

  // User supplied actions from commands
  private final List<Consumer<Command>> INIT_ACTIONS = new ArrayList<>();
  private final List<Consumer<Command>> EXECUTE_ACTIONS = new ArrayList<>();
  private final List<BiConsumer<Command, Optional<Command>>> INTERRUPT_ACTIONS = new ArrayList<>();
  private final List<Consumer<Command>> END_ACTIONS = new ArrayList<>();

  // Flag and queues for avoiding ConcurrentModificationException if commands are
  // scheduled/canceled during run
  private final Set<Command> TO_SCHEDULE_COMMANDS = new LinkedHashSet<>();
  private final List<Command> TO_CANCEL_COMMANDS = new ArrayList<>();
  private final List<Optional<Command>> TO_CANCEL_INTERRUPTERS = new ArrayList<>();
  private final Set<Command> ENDING_COMMANDS = new LinkedHashSet<>();
}
