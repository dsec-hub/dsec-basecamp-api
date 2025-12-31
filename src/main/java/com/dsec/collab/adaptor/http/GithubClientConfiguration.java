package com.dsec.collab.adaptor.http;

import com.dsec.collab.adaptor.http.GithubApiClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class GithubClientConfiguration {

    @Bean
    public GithubApiClient githubApiClient() {
        RestClient restClient = RestClient.builder()
                .build();

        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient)).build();

        return factory.createClient(GithubApiClient.class);
    }

}