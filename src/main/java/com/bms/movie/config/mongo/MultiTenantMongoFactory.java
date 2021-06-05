package com.bms.movie.config.mongo;

import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

import com.bms.movie.context.TenantContext;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;

public class MultiTenantMongoFactory extends SimpleMongoClientDatabaseFactory {

	public MultiTenantMongoFactory(MongoClient mongoClient, String databaseName) {
		super(mongoClient, databaseName);
	}

	@Override
	public MongoDatabase getMongoDatabase() {
		return getMongoClient().getDatabase(getTenantDatabase());
	}

	protected String getTenantDatabase() {
		return TenantContext.getTenant();
	}
}
