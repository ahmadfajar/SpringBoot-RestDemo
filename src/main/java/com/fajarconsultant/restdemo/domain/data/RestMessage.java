package com.fajarconsultant.restdemo.domain.data;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO model for default REST error message.
 *
 * @author Ahmad Fajar &lt;ahmadfajar@gmail.com&gt;
 * @since 27/11/2022, modified: 27/11/2022 14:53
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public record RestMessage(LocalDateTime timestamp, int status, String category,
                          String message, List<String> messages, String path) implements Serializable {
    public RestMessage(int status, String category, String message) {
        this(LocalDateTime.now(), status, category, message, null, null);
    }

    public RestMessage(int status, String category, List<String> messages) {
        this(LocalDateTime.now(), status, category, null, messages, null);
    }

    public RestMessage(int status, String category, String message, String path) {
        this(LocalDateTime.now(), status, category, message, null, path);
    }

    public RestMessage(int status, String category, List<String> messages, String path) {
        this(LocalDateTime.now(), status, category, null, messages, path);
    }
}
