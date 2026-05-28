package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ProducerResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.ProducerController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.ProducerResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class ListProducersHandler implements OperationHandler {
  private final ProducerController controller;
  private final ProducerResponsePrinter printer;

  @Override
  public void handle() {
    final List<ProducerResponse> producers = controller.listAllProducers();
    printer.printList(producers);
  }
}
