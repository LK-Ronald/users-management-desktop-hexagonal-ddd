package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.CreateProducerHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.CreateUserHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.DeleteProducerHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.DeleteUserHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.FindProducerByIdHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.FindUserByIdHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.ListProducersHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.ListUsersHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.LoginHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.OperationHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.UpdateUserHandler;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ProducerResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.UserResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.menu.MenuOption;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.ProducerController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.UserController;
import jakarta.validation.ConstraintViolationException;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class UserManagementCli {

  private static final String BANNER =
      """
      ==========================================
           Users Management System
      ==========================================""";

  private static final String MENU_BORDER = "  ==========================================";

  private final UserController userController;
  private final ProducerController producerController;
  private final ConsoleIO console;

  public void start() {
    console.println(BANNER);
    final UserResponsePrinter printer = new UserResponsePrinter(console);
    final ProducerResponsePrinter producerPrinter = new ProducerResponsePrinter(console);
    runLoop(buildHandlers(printer, producerPrinter));
  }

  private void runLoop(final Map<MenuOption, OperationHandler> handlers) {
    boolean running = true;
    while (running) {
      printMenu();
      final int choice = console.readInt("\n  Option: ");
      final Optional<MenuOption> option = MenuOption.fromNumber(choice);

      if (option.isEmpty()) {
        console.println("  Invalid option. Please try again.");
      } else if (option.get() == MenuOption.EXIT) {
        console.println("\n  Goodbye!\n");
        running = false;
      } else {
        executeHandler(handlers, option.get());
      }
    }
  }

  private void executeHandler(
      final Map<MenuOption, OperationHandler> handlers, final MenuOption option) {
    try {
      handlers.get(option).handle();
    } catch (final ConstraintViolationException exception) {
      console.println("  Validation errors:");
      exception.getConstraintViolations()
          .forEach(violation -> console.println("    - " + violation.getMessage()));
    } catch (final RuntimeException exception) {
      console.println("  Unexpected error: " + exception.getMessage());
    }
  }

  private Map<MenuOption, OperationHandler> buildHandlers(
      final UserResponsePrinter printer, final ProducerResponsePrinter producerPrinter) {
    return Map.of(
        MenuOption.LIST_USERS,      new ListUsersHandler(userController, printer),
        MenuOption.FIND_USER,       new FindUserByIdHandler(userController, console, printer),
        MenuOption.CREATE_USER,     new CreateUserHandler(userController, console, printer),
        MenuOption.UPDATE_USER,     new UpdateUserHandler(userController, console, printer),
        MenuOption.DELETE_USER,     new DeleteUserHandler(userController, console),
        MenuOption.LOGIN,           new LoginHandler(userController, console, printer),
        MenuOption.LIST_PRODUCERS,  new ListProducersHandler(producerController, producerPrinter),
        MenuOption.FIND_PRODUCER,   new FindProducerByIdHandler(producerController, console, producerPrinter),
        MenuOption.CREATE_PRODUCER, new CreateProducerHandler(producerController, console, producerPrinter),
        MenuOption.DELETE_PRODUCER, new DeleteProducerHandler(producerController, console));
  }

  private void printMenu() {
    console.println();
    console.println(MENU_BORDER);
    console.println("    Main Menu");
    console.println(MENU_BORDER);
    for (final MenuOption option : MenuOption.values()) {
      console.printf("    [%d] %s%n", option.getNumber(), option.getDescription());
    }
    console.println(MENU_BORDER);
  }
}