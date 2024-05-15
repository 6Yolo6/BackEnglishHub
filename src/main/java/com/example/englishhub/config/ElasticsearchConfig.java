//package com.example.englishhub.config;
//
//import co.elastic.clients.elasticsearch.ElasticsearchClient;
//import co.elastic.clients.transport.ElasticsearchTransport;
//import co.elastic.clients.transport.rest_client.RestClientTransport;
//import co.elastic.clients.json.jackson.JacksonJsonpMapper;
//import org.apache.http.HttpHost;
//import org.elasticsearch.client.RestClient;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @Author: hahaha
// * @Date: 2024/4/29 0:50
// */
//
//@Configuration
//public class ElasticsearchConfig {
//
//    @Bean
//    public ElasticsearchClient elasticsearchClient() {
//        RestClient restClient = RestClient.builder(
//                new HttpHost("localhost", 9200, "http")  // Elasticsearch 地址和端口
//        ).build();
//
//        ElasticsearchTransport transport = new RestClientTransport(
//                restClient,
//                new JacksonJsonpMapper()  // 使用 Jackson 作为 JSON 序列化器
//        );
//
//        return new ElasticsearchClient(transport);
//    }
//}
