package javasource;

import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.FacetField.Count;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.params.FacetParams;

public class GetFacetFromSolr {

	private SolrServer server;

	private QueryResponse response;

	private List<String> facetList;

	public GetFacetFromSolr(){
		Solr solr = new Solr();
		solr.init();
		server = solr.getServer();
	}

	public List<String> makeTrand(){

		SolrQuery query = new SolrQuery("*:*");

		query.setFacet(true);
		query.addFacetField("twit");
		query.setFacetLimit(10);
		query.setFacetMinCount(1);
		query.setFacetSort(FacetParams.FACET_SORT_COUNT);

		try {
			response = server.query(query);
		} catch (SolrServerException e) {
			e.printStackTrace();
		}

		List<FacetField> facetFieldList = response.getFacetFields();
		List<String> facet = new ArrayList<String>();

		facetList = new ArrayList<String>();

		for(FacetField f : facetFieldList){

			for(Count c : f.getValues()){
				facet.add(c.getName());
			}
		}
		facetList = facet;
		return facetList;

	}

}
