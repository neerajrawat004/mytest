package com.mongo.config;

import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;

public class RocketMorphia extends Morphia {
	public RocketDatastoreImpl createDatastore(final MongoClient mongoClient, final String dbName) {
		return new RocketDatastoreImpl(this, mongoClient, dbName);
	}
}
