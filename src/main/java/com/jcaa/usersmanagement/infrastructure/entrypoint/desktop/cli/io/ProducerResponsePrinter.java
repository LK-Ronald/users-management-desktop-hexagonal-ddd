package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.ProducerResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class ProducerResponsePrinter {

  private static final String SEPARATOR = "-".repeat(52);
  private static final String ROW_FORMAT = "  %-15s : %s%n";

  private final ConsoleIO console;

  public void print(final ProducerResponse response) {
    console.println(SEPARATOR);
    console.printf(ROW_FORMAT, "ID",            response.id());
    console.printf(ROW_FORMAT, "Name",          response.name());
    console.printf(ROW_FORMAT, "Entity Type",   response.entityType());
    console.printf(ROW_FORMAT, "Type Activity", response.typeActivity());
    console.printf(ROW_FORMAT, "Country",       response.country());
    console.printf(ROW_FORMAT, "City",          response.city());
    console.printf(ROW_FORMAT, "Street",        response.street());
    console.printf(ROW_FORMAT, "Postal Code",   response.postal());
    console.println(SEPARATOR);
  }

  public void printList(final List<ProducerResponse> producers) {
    if (producers.isEmpty()) {
      console.println("  No producers found.");
      return;
    }
    console.printf("%n  Total: %d producer(s)%n", producers.size());
    producers.forEach(this::print);
  }
}
