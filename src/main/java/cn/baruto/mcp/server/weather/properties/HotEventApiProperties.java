package cn.baruto.mcp.server.weather.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Data
@Component
public class HotEventApiProperties {

    @Value("${serper.api-key}")
    private String apikey;

}
