package com.dsec.collab.adaptor.repository.dynamoDb.configuration;

import  io.awspring.cloud.dynamodb.DynamoDbTableNameResolver;
import org.springframework.stereotype.Component;

@Component
public class SchemaTableNameResolve implements DynamoDbTableNameResolver {

    @Override
    public <T> String resolve(Class<T> clazz) {
        // utilise custom annotation's argument to resolve the table name of schema
        return clazz.getAnnotation(TableName.class).name();
    }

}
