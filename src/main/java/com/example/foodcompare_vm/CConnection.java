package com.example.foodcompare_vm;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class CConnection {
    private static MongoClient mongoClient;
    private static MongoDatabase db;
    private static MongoCollection<Document> collection;

    public MongoDatabase  connect() {
        mongoClient = MongoClients.create("mongodb+srv://fabian:12345@comparefood.stsixkp.mongodb.net/?retryWrites=true&w=majority");
        db = mongoClient.getDatabase("comparefood");
        return db;
    }

    public static MongoCollection<Document> getCollection(String name) {
        collection = db.getCollection(name);
        return collection;
    }

}
