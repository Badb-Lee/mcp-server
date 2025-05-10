package cn.baruto.mcp.server.weather.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
public class WeatherApiProperties {
    @Value("${weather.api.api-key}")
    private String apiKey;
}
