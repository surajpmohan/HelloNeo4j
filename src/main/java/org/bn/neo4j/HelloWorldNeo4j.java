package org.bn.neo4j;

import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.kernel.EmbeddedGraphDatabase;

public class HelloWorldNeo4j {
	private static final long MAX = 100000;
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
	public void createDB(){
		graphDB=new EmbeddedGraphDatabase(STORE_DIR);
	}
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
	public void readNodes(){
		for(Node node:graphDB.getAllNodes()){
			for(String key:node.getPropertyKeys()){
				System.out.println(key);
				System.out.println(node.getProperty(key));
			}
		}
	}
}
