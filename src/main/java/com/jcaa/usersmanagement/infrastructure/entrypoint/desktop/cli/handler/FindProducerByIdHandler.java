package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.domain.exception.ProducerNotFoundException;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ProducerResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.ProducerController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.ProducerResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class FindProducerByIdHandler implements OperationHandler {

  private final ProducerController controller;
  private final ConsoleIO console;
  private final ProducerResponsePrinter printer;

  @Override
  public void handle() {
    final String id = console.readRequired("Producer ID: ");
    try {
      final ProducerResponse producer = controller.findProducerById(id);
      printer.print(producer);
    } catch (final ProducerNotFoundException exception) {
      console.println("  Not found: " + exception.getMessage());
    }
  }
}
