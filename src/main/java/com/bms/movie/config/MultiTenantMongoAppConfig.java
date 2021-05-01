package com.bms.movie.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;

import com.mongodb.MongoClientSettings;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class MultiTenantMongoAppConfig extends AbstractMongoClientConfiguration {

	private final MongoConfigProperties mongoConfigProperties;

	@Bean
	MongoTransactionManager transactionManager(MongoDatabaseFactory dbFactory) {
		return new MongoTransactionManager(dbFactory);
	}

	@Override
	protected String getDatabaseName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Primary
	@Bean
	public MongoDatabaseFactory mongoDbFactory() {
		String globalDB = mongoConfigProperties.getDataBaseName();

		return new MultiTenantMongoFactory(mongoClient(), globalDB);
	}

	@Override
	@Bean
	@Primary
	public MongoTemplate mongoTemplate(MongoDatabaseFactory mongoDbFactory, MappingMongoConverter converter) {
		return new MongoTemplate(mongoDbFactory, converter);
	}

	@Bean
	public MongoTemplate mongoTemplateShared(MongoConfigProperties mongoConfigProperties) {
		MongoDatabaseFactory mongoDbFactory = new SimpleMongoClientDatabaseFactory(mongoClient(),
				mongoConfigProperties.getDataBaseName());
		return new MongoTemplate(mongoDbFactory);
	}

	@Override
	@Bean
	public MongoClient mongoClient() {

		return MongoClients.create(MongoClientSettings.builder().
				applyToClusterSettings(settings -> 
			settings.hosts(Arrays.asList(
					new ServerAddress("mongo1", 9042), 
					new ServerAddress("mongo2", 9142),
					new ServerAddress("mongo3", 9242)
					))
		).build());
	}

}
