package org.bn.neo4j;

import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.kernel.EmbeddedGraphDatabase;

public class HelloWorldNeo4j {
	private EmbeddedGraphDatabase graphDB;
	private final String STORE_DIR="/home/suraj/programs";
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HelloWorldNeo4j helloWorldNeo4j = new HelloWorldNeo4j();
		helloWorldNeo4j.createDB();
		helloWorldNeo4j.createNodes();
		helloWorldNeo4j.readNodes();
	}
	public void createDB(){
		graphDB=new EmbeddedGraphDatabase(STORE_DIR);
	}
	public void createNodes(){
		Transaction tx = graphDB.beginTx();
		try {
			Node firstNode = graphDB.createNode();
			firstNode.setProperty("message", "Hello");
			Node secondNode = graphDB.createNode();
			secondNode.setProperty("message", "World");
			tx.success();
		} 
		finally {
			tx.finish();
		}
	}
	public void readNodes(){
		for(Node node:graphDB.getAllNodes()){
		}
	}
}
