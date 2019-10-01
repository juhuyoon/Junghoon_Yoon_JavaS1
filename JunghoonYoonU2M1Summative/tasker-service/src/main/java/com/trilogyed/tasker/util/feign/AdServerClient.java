package com.trilogyed.tasker.util.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name="adserver-server-service")
public interface AdServerClient {
}
