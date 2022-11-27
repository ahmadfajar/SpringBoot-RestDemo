package com.fajarconsultant.restdemo.domain.data;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;

/**
 * The DTO model which is used to wrap the collection of data for REST response.
 *
 * @author Ahmad Fajar &lt;ahmadfajar@gmail.com&gt;
 * @since 11/03/2022, modified: 11/03/2022 17:30
 */
public record CollectionModel<T>(
        Collection<T> content, long total, int status,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime timestamp
) implements Serializable {
    public CollectionModel {
        Objects.requireNonNull(content, "content must not be null");
    }

    public CollectionModel(Collection<T> content, long total) {
        this(content, total, 200, LocalDateTime.now());
    }

    public CollectionModel(Collection<T> content, long total, int status) {
        this(content, total, status, LocalDateTime.now());
    }
}
