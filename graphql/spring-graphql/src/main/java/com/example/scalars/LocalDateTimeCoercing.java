package com.example.scalars;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import graphql.Internal;
import graphql.schema.Coercing;

@Internal
public final class LocalDateTimeCoercing implements Coercing<LocalDateTime, String> {
    @Override
    public String serialize(Object input) {
        LocalDateTime dateTime = (LocalDateTime) input;
        return DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(dateTime);
    }

    @Override
    public LocalDateTime parseValue(Object input) {
        return LocalDateTime.parse((CharSequence) input);
    }

    @Override
    public LocalDateTime parseLiteral(Object input) {
        return LocalDateTime.parse((CharSequence) input);
    }
}
