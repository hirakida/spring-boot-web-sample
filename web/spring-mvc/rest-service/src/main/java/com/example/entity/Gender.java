package com.example.entity;

import java.util.stream.Stream;

import org.jetbrains.annotations.Nullable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Gender {
    MALE,
    FEMALE;

    @JsonValue
    public String getValue() {
        return name();
    }

    @Nullable
    @JsonCreator
    public static Gender fromName(String name) {
        return Stream.of(values())
                     .filter(x -> x.name().equals(name))
                     .findFirst()
                     .orElse(null);
    }
}
