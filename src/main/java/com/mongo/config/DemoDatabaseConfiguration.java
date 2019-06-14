package com.mongo.config;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;

public class DemoDatabaseConfiguration {

	public static RocketDatastoreImpl initilaizeMongo(String hosts, String port, String daoPackage, String dbname) {

		RocketDatastoreImpl mongo;

		final RocketMorphia morphia = new RocketMorphia();

		// tell Morphia where to find your classes
		// can be called multiple times with different packages or classes

		// create the Datastore connecting to the default port on the local host
		mongo = morphia.createDatastore(makeConnection(hosts, port), dbname);
		morphia.mapPackage(daoPackage);
		mongo.ensureIndexes();
		return mongo;
	}

	private static MongoClient mongoClient;

	public static MongoClient makeConnection(String hosts, String port) {

		if (mongoClient != null) {
			return mongoClient;
		} else {
			mongoClient = makeConnectionPrivate(hosts, port);

			return mongoClient;
		}
	}

	private static MongoClient makeConnectionPrivate(String hosts, String port) {
		MongoClient mongoClient;
		// System.out.println("VolerMongoConfig " + hosts + " port " + port);
		String[] hostSplit = hosts.split(",");
		String[] portSplit = port.split(",");

		if (RocketMongoReplicaConfig.replication) {
			MongoClientOptions options = new MongoClientOptions.Builder().writeConcern(WriteConcern.SAFE)
					.connectionsPerHost(RocketMongoReplicaConfig.connectionsPerHost).maxConnectionIdleTime(60000)
					.minConnectionsPerHost(10).readPreference(ReadPreference.primaryPreferred()).build();

			List<ServerAddress> mongoinstances = new ArrayList<ServerAddress>();
			for (int i = 0; i < hostSplit.length; i++) {
				mongoinstances.add(new ServerAddress(hostSplit[i], Integer.parseInt(portSplit[i])));
			}
			mongoClient = new MongoClient(mongoinstances, options);

		} else {
			MongoClientOptions options = new MongoClientOptions.Builder()
					.connectionsPerHost(RocketMongoReplicaConfig.connectionsPerHost).minConnectionsPerHost(10)
					.maxConnectionIdleTime(60000).writeConcern(WriteConcern.SAFE).build();
			mongoClient = new MongoClient(new ServerAddress(hostSplit[0], Integer.parseInt(portSplit[0])), options);
		}
		return mongoClient;
	}

}
