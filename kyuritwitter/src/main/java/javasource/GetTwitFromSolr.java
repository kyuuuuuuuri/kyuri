package javasource;

import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import root.SuperAction;
import root.dto.SearchDto;

public class GetTwitFromSolr extends SuperAction{

	private SolrServer server;

	private QueryResponse response;

	public GetTwitFromSolr(){
		Solr solr = new Solr();
		solr.init();
		server = solr.getServer();
	}

	public List<SearchDto> getAllTwit(String searchWord, int start){

		List<SearchDto> twitList = new ArrayList<SearchDto>();
		String strId = null;
		System.out.println(searchWord + "solr");

		int id = 0;

		SolrQuery query = new SolrQuery();
		query.setQuery("twit:" + searchWord);
		query.setSortField("id", ORDER.desc);
		query.setStart(start);
		query.setRows(10);

		//Solrへクエリーを投げる
		try {
			response = server.query(query);
		} catch (SolrServerException e) {
			e.printStackTrace();
		}

		SolrDocumentList list = response.getResults();

		for(SolrDocument sd : list){
			SearchDto sDto = new SearchDto();
			strId = (String) sd.getFieldValue("id");
			id = Integer.valueOf(strId).intValue();

			sDto.setTwit((String) sd.getFieldValue("twit"));
			sDto.setId(id);

			twitList.add(sDto);
		}

		return twitList;
	}

	public List<SearchDto> getHashTwit(String hash){
		List<SearchDto> twitList = new ArrayList<SearchDto>();
		String strId = null;
		int id;

		SolrQuery query = new SolrQuery();
		query.setQuery("hash:" + hash);
		query.setSortField("id", ORDER.desc);
		query.setStart(0);
		query.setRows(10);

		//solrへ
		try {
			response = server.query(query);
		} catch (SolrServerException e) {
			e.printStackTrace();
		}

		SolrDocumentList list = response.getResults();

		for(SolrDocument sd : list){
			SearchDto sDto = new SearchDto();
			strId = (String) sd.getFieldValue("id");
			id = Integer.valueOf(strId).intValue();

			sDto.setTwit((String) sd.getFieldValue("twit"));
			sDto.setId(id);

			twitList.add(sDto);
		}

		return twitList;
	}
}
