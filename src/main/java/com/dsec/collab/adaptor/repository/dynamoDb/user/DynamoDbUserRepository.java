package com.dsec.collab.adaptor.repository.dynamoDb.user;

import com.dsec.collab.adaptor.repository.dynamoDb.schemas.UserSchema;
import com.dsec.collab.core.domain.user.User;
import com.dsec.collab.core.port.UserRepository;
import io.awspring.cloud.dynamodb.DynamoDbTemplate;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.Key;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@Repository
public class DynamoDbUserRepository implements UserRepository {


    private final DynamoDbTemplate dynamoDbTemplate;

    public DynamoDbUserRepository(DynamoDbTemplate dynamoDbTemplate) {
        this.dynamoDbTemplate = dynamoDbTemplate;
    }

    @Override
    public User getUser(UUID id) {

        // build the key to search dynamodb with
        Key partitionKey = Key.builder().partitionValue(id.toString()).build();

        // use key to load user row into memory
        UserSchema userSchema = dynamoDbTemplate.load(partitionKey, UserSchema.class);

        assertThat(userSchema)
                .isNotNull();

        // return User object from user retrieved row
        return this.userSchemaToUser(userSchema);
    }

    @Override
    public User updateUser(UUID id, User user) {
        return null;
    }

    // private method for schema -> domain
    private User userSchemaToUser(UserSchema userSchema) {
        return User.UserFactory(
                userSchema.getId(),
                userSchema.getEmail(),
                userSchema.getUsername()
        );
    }

    // private method for domain -> schema
    private UserSchema userToUserSchema(User user) {
        return new UserSchema(
                user.getId(),
                user.getEmail(),
                user.getUsername()
        );
    }
}