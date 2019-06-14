package com.mongo.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mongodb.morphia.DatastoreImpl;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class RocketDatastoreImpl extends DatastoreImpl {

	public RocketDatastoreImpl(Morphia morphia, MongoClient mongoClient, String dbName) {
		super(morphia, morphia.getMapper(), mongoClient, dbName);
	}

	@Override
	public <T> Key<T> save(final T entity) {

		Entity e = entity.getClass().getAnnotation(Entity.class);

		e.value();

		Key<T> data = super.save(entity);

		return data;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.mongodb.morphia.DatastoreImpl#update(org.mongodb.morphia.query.Query,
	 * org.mongodb.morphia.query.UpdateOperations)
	 */
	@Override
	public <T> UpdateResults update(final Query<T> query, final UpdateOperations<T> operations) {

		boolean createHistory = false;
		List<Object> updatedRecordIds = null;

		UpdateResults updateResults = super.update(query, operations);

		try {

			Entity e = query.getEntityClass().getAnnotation(Entity.class);

			DBCollection dbCollectionHistory = getCollection(e.value() + "_history");

			if (createHistory) {

				BasicDBObject searchQuery = new BasicDBObject();
				Map<String, Object> whereValue = new HashMap<String, Object>();
				whereValue.put("$in", updatedRecordIds);
				DBCollection dbColl = query.getCollection();
				searchQuery.put("_id", whereValue);
				if (dbColl == null) {
					dbColl = getCollection(query.getEntityClass());
				}
				DBCursor cursor = dbColl.find(searchQuery);

				if (cursor != null) {

					for (DBObject document : cursor) {
						document.removeField("_id");
						dbCollectionHistory.save(document);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return updateResults;
	}

	/**
	 * Gets the all super class names.
	 *
	 * @param entityClass the entity class
	 * @return the all super class names
	 */
	private List<String> getAllSuperClassNames(Class entityClass) {
		List<String> superClasses = new ArrayList<>();
		entityClass = entityClass.getSuperclass();
		while (entityClass != null) {
			superClasses.add(entityClass.getSimpleName());
			entityClass = entityClass.getSuperclass();

		}
		return superClasses;
	}

	/**
	 * Gets the updated record ids.
	 *
	 * @param <T>   the generic type
	 * @param query the query
	 * @return the updated record ids
	 */
	private <T> List<Object> getUpdatedRecordIds(final Query<T> query) {

		List<Object> updatedRecordIds = new ArrayList<>();

		DBCollection dbColl = query.getCollection();

		if (dbColl == null) {
			dbColl = getCollection(query.getEntityClass());
		}
		DBObject queryObject = query.getQueryObject();
		if (queryObject == null) {
			queryObject = new BasicDBObject();
		}

		DBCursor cursor = dbColl.find(queryObject);
		if (cursor != null) {

			for (DBObject document : cursor) {
				// dbCollectionHistory.save(document);
				updatedRecordIds.add(document.get("_id"));

			}
		}

		return updatedRecordIds;
	}
}
