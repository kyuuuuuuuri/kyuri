package javasource;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.seasar.struts.annotation.Execute;

import root.entity.Murmur;

public class SetTwitToSolr{

	private SolrServer server;

	private SolrInputDocument document;

	public SetTwitToSolr(){
		Solr solr = new Solr();
		solr.init();
		server = solr.getServer();
		document = new SolrInputDocument();
	}

	@Execute(validator = false)
	public void setTwit(Murmur twitInfo, String[] hash){
		String id = Integer.toString(twitInfo.murmurid);
		document.addField("id", id);
		document.addField("twit", twitInfo.murmur);
		document.addField("date", twitInfo.dateTime);
		if(hash != null){
			document.addField("hash", hash);
		}

		//serverに格納
		try {
			server.add(document);
			server.commit();
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	public void deleteTwit(int twitid){
		String id = Integer.toString(twitid);

		try {
			server.deleteById(id);
			server.commit();
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
