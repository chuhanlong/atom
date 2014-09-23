package es.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.deletebyquery.DeleteByQueryResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;

import es.domain.Medicine;

public class ElasticSearchHandler {

	private Client client;

	public ElasticSearchHandler() {
		// 使用本机做为节点
		this("localhost");
	}

	public ElasticSearchHandler(String ipAddress) {
		Settings settings = ImmutableSettings.settingsBuilder()
//				.put("cluster.name", "elasticsearch")// 集群的name
				.put("client.transport.sniff", true)// 使客户端去嗅探整个集群的状态
				.put("client.transport.ping_timeout", "10s")// 集群连接超时设置
				.build();
		client = new TransportClient(settings)
		// .addTransportAddress(new InetSocketTransportAddress(ipAddress, 9300))
				.addTransportAddress(new InetSocketTransportAddress(
						"127.0.0.1", 9300));
		// Node node =
		// NodeBuilder.nodeBuilder().clusterName("War").client(true).node();
		// Node node = NodeBuilder.nodeBuilder().local(true).node();
		// client = node.client();
	}

	/**
	 * 建立索引,索引建立好之后,会在elasticsearch-0.20.6\data\elasticsearch\nodes\0创建所以你看
	 * 
	 * @param indexName
	 *            为索引库名，一个es集群中可以有多个索引库。 名称必须为小写
	 * @param indexType
	 *            Type为索引类型，是用来区分同索引库下不同类型的数据的，一个索引库下可以有多个索引类型。
	 * @param jsondata
	 *            json格式的数据集合
	 * 
	 * @return
	 * @throws IOException 
	 */
	public void createIndexResponse(String indexname, String type,
			List<String> jsondata) throws IOException {
//		 创建索引库 需要注意的是.setRefresh(true)这里一定要设置,否则第一次建立索引查找不到数据
		IndexRequestBuilder requestBuilder = client.prepareIndex(indexname,
				type).setRefresh(true);
		for (int i = 0; i < jsondata.size(); i++) {
			requestBuilder.setSource(jsondata.get(i)).execute().actionGet();
		}
//		//另一种批量添加索引及数据
//		BulkRequestBuilder bulkRequest = client.prepareBulk();   
//		//使用XContentBuilder创建json数据
//		for (String string : jsondata) {
//			bulkRequest.add(client.prepareIndex(indexname, type).setSource(string));
//		}
//        BulkResponse bulkResponse = bulkRequest.execute().actionGet();   
//        if (bulkResponse.hasFailures()) {   
//            System.out.println(bulkResponse.buildFailureMessage());
//        }  
	}

	/**
	* 创建索引名称
	* @param indices 索引名称
	*/
	public  void createIndex(String indices){
		CreateIndexResponse response = client.admin().indices().prepareCreate(indices).execute().actionGet();
		System.out.println(response);
	}
	
	/**
	 * 创建索引
	 * 
	 * @param client
	 * @param jsondata
	 * @return
	 */
	public IndexResponse createIndexResponse(String indexname, String type,
			String jsondata) {
		IndexResponse response = client.prepareIndex(indexname, type)
				.setSource(jsondata).execute().actionGet();
		return response;
	}

	/**
	 * 通过id、删除索引数据
	 * 
	 * @param indexname
	 * @param type
	 * @param jsondata
	 * @return
	 */
	public DeleteResponse deleteDataById(String indexname, String type,
			String id) {
		DeleteResponse response = client.prepareDelete().setIndex(indexname)
				.setType(type).setId(id).execute()
				.actionGet();
		// DeleteResponse response =
		// client.prepareDelete(indexname,type,"6HL0sCtkS4q9-axBsBj3qw").execute().actionGet();//删除的id为_id，不是自定义的id
		return response;
	}

	/**
	 * 通过query、删除索引数据
	 * 
	 * @param indexname
	 * @param type
	 * @param jsondata
	 * @return
	 */
	public DeleteByQueryResponse deleteDataByQuery(String indexname,
			String type, QueryBuilder query) {
		DeleteByQueryResponse response = client.prepareDeleteByQuery(indexname)
				.setQuery(query).execute().actionGet();
		return response;
	}

	/**
	 * 执行搜索
	 * 
	 * @param queryBuilder
	 * @param indexname
	 * @param type
	 * @return
	 */
	public List<Medicine> searcher(QueryBuilder queryBuilder, String indexname,
			String type) {
		List<Medicine> list = new ArrayList<Medicine>();
		SearchResponse searchResponse = client.prepareSearch(indexname)
				.setTypes(type).setQuery(queryBuilder).execute().actionGet();
		SearchHits hits = searchResponse.getHits();
		System.out.println("查询到记录数=" + hits.getTotalHits());
		SearchHit[] searchHists = hits.getHits();
		if (searchHists.length > 0) {
			for (SearchHit hit : searchHists) {
				Integer id = (Integer) hit.getSource().get("id");
				String name = (String) hit.getSource().get("name");
				String function = (String) hit.getSource().get("function");
				String idid = hit.getId();// 获取自身_id
				list.add(new Medicine(id, name, function));
			}
		}
		return list;
	}

	/**
	 * 创建mapping(feid("indexAnalyzer","ik")该字段分词IK索引
	 * ；feid("searchAnalyzer","ik")该字段分词ik查询；具体分词插件请看IK分词插件说明)
	 * 
	 * @param indices
	 *            索引名称；
	 * @param mappingType
	 *            索引类型
	 * @throws Exception
	 */
	public  void createMapping(String indices, String mappingType)
			throws Exception {
		XContentBuilder builder = XContentFactory.jsonBuilder().startObject()
				.startObject(mappingType).startObject("properties")
				.startObject("id").field("type", "long").field("store", "yes").endObject()
				.startObject("name").field("type", "string").field("store", "yes").field("indexAnalyzer", "ik").field("searchAnalyzer", "ik").endObject()
				.startObject("function").field("type", "string").field("store", "yes").field("indexAnalyzer", "ik").field("searchAnalyzer", "ik").endObject()
				.endObject()
				.endObject().endObject();
		System.out.println(builder.string());
		PutMappingRequest mapping = Requests.putMappingRequest(indices)
				.type(mappingType).source(builder);
		client.admin().indices().putMapping(mapping).actionGet();
	}
	
	public static void main(String[] args) throws Exception {
		ElasticSearchHandler esHandler = new ElasticSearchHandler();
		List<String> jsondata = DataFactory.getInitJsonData();
		String indexname = "indextest7";// 索引库名，必须小写，一个es集群可以有多个索引库
		String type = "typeTest7"; // 索引类型,是来区分同索引库下不同类型的数据

		esHandler.createIndex(indexname);
		esHandler.createMapping(indexname, type);//创建mapping
		 esHandler.createIndexResponse(indexname, type, jsondata);//创建索引数据
		 
		 //delete
//		DeleteResponse response = esHandler.deleteDataById(indexname, type,
//				jsondata.get(0));// 按id删除
//		System.out.println(response.getIndex() + "  " + response.getType()
//				+ " " + response.getId());
		// 查询条件
		
//		QueryBuilder queryBuilder = QueryBuilders.termQuery("id", "1000001");
//		esHandler.deleteDataByQuery(indexname, type, queryBuilder);// 按查询条件删除
//		 QueryBuilder queryBuilder = QueryBuilders.fuzzyQuery("name", "感冒");//模糊查询
		 QueryBuilder queryBuilder = QueryBuilders.wildcardQuery("name", "中?");//通配符查询 ?  ~
//		 QueryBuilder queryBuilder = QueryBuilders.boolQuery()
//		 .mustNot(QueryBuilders.termQuery("id", 1)).should(QueryBuilders.wildcardQuery("name", "糖?"));
//			QueryBuilder queryBuilder = QueryBuilders.queryString("中国的人们国歌");//! ^ * () 报错或查不出来

//		QueryBuilder qb3 = QueryBuilders.filteredQuery(
//				QueryBuilders.termQuery("name.first", "shay"),
//				rangeFilter("age").from(23).to(54).includeLower(true)
//						.includeUpper(false));
		long before = System.currentTimeMillis();
		List<Medicine> result = esHandler.searcher(queryBuilder, indexname,
				type);
		long after = System.currentTimeMillis();
		System.out.println("spend time :" + (after - before) + "ms");
		for (int i = 0; i < result.size(); i++) {
			Medicine medicine = result.get(i);
			System.out.println("(" + medicine.getId() + ")		药品名称:"
					+ medicine.getName() + "\t\t" + medicine.getFunction());
		}
	}
}