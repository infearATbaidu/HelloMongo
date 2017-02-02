import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Updates.set;

/**
 * Created by infear on 2017/1/29.
 */
public class FirstConnection {
    public static void main(String args[]){
        MongoClientURI connectionString = new MongoClientURI("mongodb://localhost:27017");
        MongoClient client = new MongoClient(connectionString);

        MongoCollection<Document> collection = client.getDatabase("newDB").getCollection("newCollection");
        Document doc = new Document("name", "MongoDB")
                .append("type", "database")
                .append("count", 1)
                .append("info", new Document("x", 203).append("y", 102));
        collection.insertOne(doc);

        doc = collection.find(eq("name","MongoDB")).projection(excludeId()).first();
        System.out.println(doc.toJson());

        collection.updateOne(eq("name","MongoDB"),set("count",2));
        doc = collection.find(eq("name","MongoDB")).projection(excludeId()).first();
        System.out.println(doc.toJson());

        collection.deleteMany(and(eq("name","MongoDB")));
    }
}
