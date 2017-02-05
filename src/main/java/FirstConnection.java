import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import conn.ClientFactory;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Updates.set;

/**
 * Created by infear on 2017/1/29.
 */
public class FirstConnection {
    public static void main(String args[]){
        MongoClient client = ClientFactory.createWithOptions();

        // insert
        MongoCollection<Document> collection = client.getDatabase("newDB").getCollection("newCollection");
        Document doc = new Document("name", "MongoDB")
                .append("type", "database")
                .append("count", 1)
                .append("info", new Document("x", 203).append("y", 102));
        collection.insertOne(doc);

        // find and projection
        doc = collection.find(eq("name","MongoDB")).projection(excludeId()).first();
        System.out.println(doc.toJson());

        // update
        collection.updateOne(eq("name", "MongoDB"), set("count", 3));
        doc = collection.find(eq("name","MongoDB")).projection(excludeId()).first();
        System.out.println(doc.toJson());

        // close
        client.close();
    }
}
