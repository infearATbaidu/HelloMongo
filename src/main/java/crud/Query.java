package crud;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import conn.ClientFactory;
import conn.Config;
import org.bson.Document;

/**
 * Created by infear on 2017/2/5.
 */
public class Query {
    public static void main(String args[]) {
        MongoClient client = ClientFactory.create();
        MongoCollection<Document> collection = client.getDatabase(Config.DB).getCollection(Config.COLLECTION);
        Document doc = collection.find(Filters.eq("info.x", 203)).first();
        System.out.println(doc.toJson());

        Document docProject = collection.find(Filters.eq("info.x", 203)).projection(Projections.exclude("_id")).first();
        System.out.println(docProject.toJson());
    }
}
