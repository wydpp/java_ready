package com.wydpp;/**
 * Created by wydpp on 2021/5/7.
 */

import org.apache.http.HttpHost;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermsQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wydpp
 **/
public class EsClient {

    public static void main(String[] args) throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(
            RestClient.builder(
                new HttpHost("localhost", 9200, "http"),
                new HttpHost("localhost", 9201, "http")));
        search(client);
//        BulkRequest request = new BulkRequest();
//        for (int i = 1; i < 100000; i++) {
//            IndexRequest indexRequest = new IndexRequest("equipment").id(i + "").type("doc");
//            Map<String, Object> jsonMap = new HashMap<>();
//            jsonMap.put("eq_id", i + "");
//            jsonMap.put("day", "2021-05-07");
//            jsonMap.put("order_id", i + "");
//            jsonMap.put("duration", 15);
//            indexRequest.source(XContentType.JSON).source(jsonMap);
//            request.add(indexRequest);
//            if (i % 1000 == 0){
//                client.bulk(request, RequestOptions.DEFAULT);
//                request = new BulkRequest();
//            }
//        }
        client.close();
    }

    public static void search(RestHighLevelClient client) throws IOException {
        //String[] eqIds = new String[100000];
        List<String> eqIds = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            eqIds.add((i+1) + "");
        }
        TermsQueryBuilder  termsFilterBuilder = new TermsQueryBuilder ("eq_id", eqIds);
        SearchSourceBuilder builder = new SearchSourceBuilder()
            .postFilter(termsFilterBuilder);
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.searchType(SearchType.DFS_QUERY_THEN_FETCH);
        searchRequest.source(builder);
        searchRequest.indices("equipment");
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        for (SearchHit searchHits : response.getHits()) {
            System.out.println(searchHits);
        }
    }

    public static void insertData(RestHighLevelClient client) {
        // 1、创建索引请求
        IndexRequest request = new IndexRequest(
            "equipment",   //索引
            "doc",     // mapping type
            "1");     //文档id
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("eq_id", "EQ123456");
        jsonMap.put("day", "2021-05-07");
        jsonMap.put("order_id", "169");
        jsonMap.put("duration", 15);
        request.source(jsonMap);
        IndexResponse indexResponse = null;
        try {
            // 同步方式
            indexResponse = client.index(request, RequestOptions.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //5、处理响应
        if (indexResponse != null) {
            String index = indexResponse.getIndex();
            String type = indexResponse.getType();
            String id = indexResponse.getId();
            long version = indexResponse.getVersion();
            if (indexResponse.getResult() == DocWriteResponse.Result.CREATED) {
                System.out.println("新增文档成功，处理逻辑代码写到这里。");
            } else if (indexResponse.getResult() == DocWriteResponse.Result.UPDATED) {
                System.out.println("修改文档成功，处理逻辑代码写到这里。");
            }
        }
    }

}
