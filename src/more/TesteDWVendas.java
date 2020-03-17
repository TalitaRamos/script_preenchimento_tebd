package more;

import com.mongodb.*;

import java.util.ArrayList;
import java.util.List;

public class TesteDWVendas {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        MongoClient mongoClient = new MongoClient("localhost", 27017);
		
		mongoClient.getDatabaseNames().forEach(System.out::println);
		
		DB database = mongoClient.getDB("DWVENDAS");
		
		DBCollection collection = database.getCollection("VENDAS");
		
		BasicDBObject document = new BasicDBObject();
		
		document.put("QUANTIDADE", 10);
		document.put("LOJA", "LOJA1");
		document.put("ESTADO", "SP");
		
		document.put("PRODUTO", "P1");
		document.put("CATEGORIA", "CAT1");
		document.put("FAMILIA", "F1");
		document.put("DIA", 20);
		document.put("MES", 1);
		document.put("ANO", 2020);
		collection.insert(document);
		
		BasicDBObject query = new BasicDBObject();
		query.put("ESTADO", "SP");
		
		BasicDBObject newDocument = new BasicDBObject();
		newDocument.put("QUANTIDADE", 2000);
		
		BasicDBObject updateObject = new BasicDBObject();
		updateObject.put("$set", newDocument);
		 
		collection.updateMulti(query, updateObject);
		
        BasicDBObject searchQuery = new BasicDBObject();
		
		List<String> list = new ArrayList<String>();
		//list.add("BA");
	 	list.add("SP");
		//list.add(5);
		searchQuery.put("ESTADO", new BasicDBObject("$in", list));
		//searchQuery.put("name", "John");
		//searchQuery.put("name", "Shubham");
		DBCursor cursor = collection.find(searchQuery);
		 int x=0;
		while (cursor.hasNext()) {
		    System.out.println(cursor.next());
		    x=x+1;
		}
		
		System.out.println("x="+x);
	}

}
