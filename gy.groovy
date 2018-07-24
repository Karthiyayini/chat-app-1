@Grab('org.mongodb:mongodb-driver:3.4.1')

import com.mongodb.MongoClient
import com.mongodb.MongoClientURI
import com.mongodb.DBCollection
import com.mongodb.DB
import com.mongodb.DBCursor;
import com.mongodb.BasicDBObject
import com.mongodb.DBObject;
import groovy.json.JsonSlurper;

class MongoService {
    private MongoClient mongoClient

    def login = "admin"
    def password = "admin123"
    def host = "ds245971.mlab.com"
    def port = 45971
    def databaseName = 'jenkin-matrix'

    public MongoClient client() {
        mongoClient = new MongoClient(new MongoClientURI(
               new StringBuilder("mongodb://").
               append(login).append(":").
               append(password).append("@").
               append(host).append(":").
               append(port).append("/").
               append(databaseName).toString()))
                              ;

        return mongoClient
    }

    public DBCollection collection(collectionName) {
        DB db = client().getDB(databaseName)

        return db.getCollection(collectionName)
    }
}

def service = new MongoService(databaseName: 'jenkin-matrix') 
def foo = service.collection('registry')

BasicDBObject whereQuery = new BasicDBObject();
    whereQuery.put("job", "document_name");

    DBCursor cursor = foo.find(whereQuery);

    if (cursor.hasNext()) {
       // log.info(cursor.next())
      	println cursor.next()
    }else{
        println "not fount"
    }
	
