package com.dsec.collab.adaptor.repository.dynamoDb.schemas;

import com.dsec.collab.adaptor.repository.dynamoDb.configuration.TableName;
import lombok.*;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamoDbBean
@TableName(name="users")
public class UserSchema {
    private UUID id;
    private String email;
    private String username;

    @DynamoDbPartitionKey
    public UUID getId() {
        return id;
    }
}
