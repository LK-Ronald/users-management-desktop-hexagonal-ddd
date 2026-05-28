package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ProducerResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.ProducerController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.CreateProducerRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.ProducerResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class CreateProducerHandler implements OperationHandler {

  private final ProducerController controller;
  private final ConsoleIO console;
  private final ProducerResponsePrinter printer;

  @Override
  public void handle() {
    final String id             = console.readRequired("ID                                      : ");
    final String name           = console.readRequired("Name                                    : ");
    final String entityType     = console.readRequired("Entity Type (COMPANY / ORGANIZATION)    : ");
    final String typeActivity   = console.readRequired("Type Activity (COMMERCIAL/INDUSTRIAL/SERVICE) : ");
    final String country        = console.readRequired("Country                                 : ");
    final String city           = console.readRequired("City                                    : ");
    final String street         = console.readRequired("Street & Number                         : ");
    final String postal         = console.readRequired("Postal Code                             : ");

    try {
      final ProducerResponse created =
          controller.createProducer(new CreateProducerRequest(id, name, entityType, typeActivity, country, city, street, postal));
      console.println("\n  Producer created successfully.");
      printer.print(created);
    } catch (final RuntimeException exception) {
      console.println("  Error: " + exception.getMessage());
    }
  }
}
