package org.bn.neo4j;

import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.kernel.EmbeddedGraphDatabase;

/**
 * Simple program to understand how embedded neo4j db can be used with java.
 * @author suraj
 * Testing.
 */
public class HelloWorldNeo4j {
	private static final long MAX = 1000;
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
		helloWorldNeo4j.deleteNodes();
	}
	/**
	 * Delete all the nodes present in the db.
	 */
	public void deleteNodes() {
		Transaction tx = graphDB.beginTx();
		try {
			for(Node node:graphDB.getAllNodes()){
				node.delete();
			}
			tx.success();
		} 
		finally {
			tx.finish();
		}
	}
	/**
	 * Creates a new neo4j db.
	 */
	public void createDB(){
		graphDB=new EmbeddedGraphDatabase(STORE_DIR);
	}
	/**
	 * Creates the new nodes.
	 */
	public void createNodes(){
		Transaction tx = graphDB.beginTx();
		try {
			for(long i=0; i< MAX; i++){
				Node firstNode = graphDB.createNode();
				firstNode.setProperty(i+"", i + " Hello!! World");
			}
			tx.success();
		} 
		finally {
			tx.finish();
		}
	}
	/**
	 * Reading all values.
	 */
	public void readNodes(){
		for(Node node:graphDB.getAllNodes()){
			for(String key:node.getPropertyKeys()){
				System.out.println(key);
				System.out.println(node.getProperty(key));
			}
		}
	}
}
