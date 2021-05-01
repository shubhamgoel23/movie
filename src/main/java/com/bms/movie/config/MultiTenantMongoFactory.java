package com.bms.movie.config;

import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;

public class MultiTenantMongoFactory extends SimpleMongoClientDatabaseFactory {

	private final String globalDbName;



	public MultiTenantMongoFactory(MongoClient mongoClient, String globalDbName) {
		super(mongoClient, globalDbName);
		this.globalDbName = globalDbName;
	}

	@Override
	public MongoDatabase getMongoDatabase() {
		return getMongoClient().getDatabase(getTenantDatabase());
	}

	protected String getTenantDatabase() {
		String tenantId = TenantContext.getTenantId();
		if (tenantId != null) {
			return tenantId;
		} else {
			return globalDbName;
		}
	}
}
