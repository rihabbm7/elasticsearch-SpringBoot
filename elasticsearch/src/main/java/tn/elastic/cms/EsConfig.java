package tn.elastic.cms;

import java.net.InetAddress;
import java.net.InetSocketAddress;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "tn.elastic.cms.repository")
@ComponentScan(basePackages = { "tn.elastic.cms.data.es.service" })
public class EsConfig {
	 	@Value("${elasticsearch.host}")
	    private String EsHost;

	    @Value("${elasticsearch.port}")
	    private int EsPort;

	    @Value("${elasticsearch.clustername}")
	    private String EsClusterName;

	    @Bean
	    public RestHighLevelClient client() throws Exception {

	    	ClientConfiguration clientConfiguration 
            = ClientConfiguration.builder()
                .connectedTo("localhost:9200")
                .build();

        return RestClients.create(clientConfiguration).rest();
	    }

	    @Bean
	    public ElasticsearchOperations elasticsearchTemplate() throws Exception {
	        return new ElasticsearchRestTemplate(client());
	    }

	    //Embedded Elasticsearch Server
	    /*@Bean
	    public ElasticsearchOperations elasticsearchTemplate() {
	        return new ElasticsearchTemplate(nodeBuilder().local(true).node().client());
	    }*/

	
}
