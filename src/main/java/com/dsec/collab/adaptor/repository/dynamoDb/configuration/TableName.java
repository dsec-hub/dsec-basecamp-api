package com.dsec.collab.adaptor.repository.dynamoDb.configuration;

import org.assertj.core.util.diff.Delta;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(TYPE)
@Retention(RUNTIME)
public @interface TableName {
    // spring cloud will use this annotation's value when searching for table
    String name();
}
