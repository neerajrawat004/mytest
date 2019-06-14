package com.mongo.config;


public interface RocketMongoReplicaConfig {
	boolean replication = true;
	int connectionsPerHost = 10;
}
