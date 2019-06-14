package com.mongo.config;

import org.mongodb.morphia.Datastore;

public class RocketMongoConfig {

	private static RocketDatastoreImpl mongo;

	/**
	 * @return the mongo
	 */
	public static Datastore getMongo() {
		if (mongo == null) {
			initilaizeMongo();
		}
		return mongo;
	}

	private static void initilaizeMongo() {

		String mongoHost = System.getProperty("mongo.host");

		if (mongoHost == null || mongoHost.trim().isEmpty()) {
			mongoHost = "127.0.0.1";
		}

		String mongoPort = System.getProperty("mongo.port");

		if (mongoPort == null || mongoPort.trim().isEmpty()) {
			mongoPort = "27052";
		}

		mongoHost = mongoHost.trim();
		mongoPort = mongoPort.trim();

		mongo = DemoDatabaseConfiguration.initilaizeMongo(mongoHost, mongoPort, "com.rocket.seo", "rocket-seo");
	}

}
