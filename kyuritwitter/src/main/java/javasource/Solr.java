package javasource;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;

public class Solr {

	private SolrServer server;

//	private static final String URL = "http://localhost:8080/solr";
	private static final String URL = "http://54.248.81.45/solr";


	public void init(){
		setServer(new HttpSolrServer(URL));
	}

	public SolrServer getServer() {
		return server;
	}

	public void setServer(SolrServer server) {
		this.server = server;
	}


}
