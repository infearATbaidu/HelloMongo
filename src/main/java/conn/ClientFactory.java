package conn;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;

/**
 * Created by infear on 2017/2/5.
 */
public class ClientFactory {
    public static MongoClient create() {
        return new MongoClient(Config.HOST, Config.PORT);
    }

    public static MongoClient createByURI() {
        return new MongoClient(new MongoClientURI(String.format("mongodb://%s:%d", Config.HOST, Config.PORT)));
    }

    public static MongoClient createWithOptions() {
        MongoClientOptions options = MongoClientOptions.builder().
                cursorFinalizerEnabled(false).
                connectTimeout(30).
                build();
        return new MongoClient(new ServerAddress(Config.HOST, Config.PORT), options);
    }

}
