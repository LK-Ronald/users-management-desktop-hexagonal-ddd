package com.jcaa.usersmanagement.application.service.dto.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateProducerCommand(@NotBlank(message = "id must not be blank")
                                    String id,
                                    @NotBlank(message = "name must not be blank")
                                    @Size(min = 3, message = "name must have at least 3 characters")
                                    String name,
                                    @NotBlank(message = "entity Type must not be blank")
                                    String entityType,
                                    @NotBlank(message = "type Activity must not be blank")
                                    String typeActivity,
                                    @NotBlank(message = "country must not be blank")
                                    @Size(min = 3, message = "country must have at least 3 characters")
                                    String country,
                                    @NotBlank(message = "city must not be blank")
                                    @Size(min = 3, message = "city must have at least 3 characters")
                                    String city,
                                    @NotBlank(message = "street must not be blank")
                                    String street,
                                    @NotBlank(message = "postal must not be blank")
                                    String postal)
{

}
