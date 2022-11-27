package com.fajarconsultant.restdemo.domain.data;

import java.util.UUID;

/**
 * A Projection for the {@link com.fajarconsultant.restdemo.entity.Client} entity.
 */
public interface IClientInfo {
    UUID getId();

    String getName();
}