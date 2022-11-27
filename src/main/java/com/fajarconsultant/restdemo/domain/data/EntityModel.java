package com.fajarconsultant.restdemo.domain.data;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * DTO model which is used to wrap the data for REST response.
 *
 * @author Ahmad Fajar &lt;ahmadfajar@gmail.com&gt;
 * @since 27/11/2022, modified: 27/11/2022 14:08
 */
public record EntityModel<T>(
        T content, int status,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime timestamp
) implements Serializable {
    public EntityModel {
        Objects.requireNonNull(content, "content must not be null");
    }

    public EntityModel(T content) {
        this(content, 200, LocalDateTime.now());
    }

    public EntityModel(T content, int status) {
        this(content, status, LocalDateTime.now());
    }
}
