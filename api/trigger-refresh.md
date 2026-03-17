# Trigger Refresh API

Sends a config refresh event to all connected clients via Spring Cloud Bus (RabbitMQ).

When triggered, all clients (e.g. `demo-api-client`) will receive a `RefreshRemoteApplicationEvent` and print "Hello World!" to the console.

Base URL: `http://localhost:8888`

---

## Trigger Config Refresh Event

### curl

```bash
curl -s -X POST http://localhost:8888/api/trigger-refresh
```

### PowerShell

```powershell
Invoke-RestMethod -Uri http://localhost:8888/api/trigger-refresh -Method Post
```

### Response (200 OK)

```json
{
  "status": "Refresh event sent to all connected clients via Spring Cloud Bus"
}
```

---

## Alternative: Using Actuator Bus Refresh

You can also trigger a refresh directly via the Spring Boot Actuator endpoint:

### curl

```bash
curl -s -X POST http://localhost:8888/actuator/busrefresh
```

### PowerShell

```powershell
Invoke-RestMethod -Uri http://localhost:8888/actuator/busrefresh -Method Post
```

### Response

```
204 No Content
```

---

## What Happens

1. Config server publishes a `RefreshRemoteApplicationEvent` to RabbitMQ.
2. All clients subscribed via Spring Cloud Bus receive the event.
3. `demo-api-client` logs: `Hello World! Received config refresh event from: <origin>`
