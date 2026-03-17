package com.example.configserver.controller;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.cloud.bus.endpoint.RefreshBusEndpoint;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
@ConditionalOnBean(RefreshBusEndpoint.class)
public class TriggerController {

	private final RefreshBusEndpoint refreshBusEndpoint;

	public TriggerController(RefreshBusEndpoint refreshBusEndpoint) {
		this.refreshBusEndpoint = refreshBusEndpoint;
	}

	@PostMapping("/trigger-refresh")
	public ResponseEntity<Map<String, String>> triggerRefresh() {
		refreshBusEndpoint.busRefresh();
		return ResponseEntity.ok(Map.of("status", "Refresh event sent to all connected clients via Spring Cloud Bus"));
	}

}
