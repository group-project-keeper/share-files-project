package org.sharefiles.root.config;

import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;


@Configuration
public class MongoConfig {

    public @Bean
    MongoDatabaseFactory mongoDbFactory() throws Exception {
        return new SimpleMongoClientDatabaseFactory(MongoClients.create(), ShareFilesConfig.MONGO_DATABASE_NAME);
    }

    public @Bean
    MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongoDbFactory());
    }




}
