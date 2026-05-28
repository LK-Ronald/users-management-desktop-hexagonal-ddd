package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.domain.exception.ProducerNotFoundException;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.ProducerController;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class DeleteProducerHandler implements OperationHandler {

  private final ProducerController controller;
  private final ConsoleIO console;

  @Override
  public void handle() {
    final String id = console.readRequired("Producer ID: ");
    try {
      controller.deleteProducer(id);
      console.println("\n  Producer deleted successfully.");
    } catch (final ProducerNotFoundException exception) {
      console.println("  Not found: " + exception.getMessage());
    }
  }
}
