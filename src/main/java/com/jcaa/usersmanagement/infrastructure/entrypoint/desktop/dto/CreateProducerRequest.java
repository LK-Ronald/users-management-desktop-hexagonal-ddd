package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto;

public record CreateProducerRequest(String id,
                                    String name,
                                    String entityType,
                                    String typeActivity,
                                    String country,
                                    String city,
                                    String street,
                                    String postal
){ }
