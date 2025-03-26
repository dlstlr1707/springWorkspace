package com.example.frontservice.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "catalogClient", url = "${polar.edge-service-url}/books")
public interface CatalogClient {

}
