package cn.baruto.mcp.server.weather.service;

import cn.baruto.mcp.server.weather.bean.weather.WeatherFunctionRequest;
import cn.baruto.mcp.server.weather.properties.WeatherApiProperties;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import jakarta.annotation.Resource;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class WeatherService {

    @Resource
    private WeatherApiProperties weatherApiProperties;

    @Tool(description = "获取某个城市的实时天气")
    public String getWeather(WeatherFunctionRequest request) {
        String city = request.getCity();
        log.info("开始获取天气，城市：{}",city);
        // 先调用城市搜索接口，查询到该城市的locationId
        String citySearchApiUrl = "https://your.host.com/geo/v2/city/lookup";
        @Cleanup
        HttpResponse citySearchHttpResponse = HttpUtil.createGet(citySearchApiUrl)
                .header("Content-Type", "application/json")
                .header("X-QW-Api-Key", weatherApiProperties.getApiKey())
                .form("location", city)
                .execute();

        String cityResponseBody = citySearchHttpResponse.body();
        log.info("城市搜索接口返回结果：{}", cityResponseBody);
        String locationId = JSONUtil.getByPath(JSONUtil.parseObj(cityResponseBody), "$.location[0].id", null);

        log.info("城市的locationId为：{}", locationId);
        // 再调用天气接口，获取天气信息
        @Cleanup
        HttpResponse weatherHttpResponse = HttpUtil.createGet("https://your.host.com/v7/weather/now")
                .header("Content-Type", "application/json")
                .header("X-QW-Api-Key", weatherApiProperties.getApiKey())
                .form("location", locationId)
                .form("lang", "zh")
                .execute();
        String body = weatherHttpResponse.body();
        log.info("天气接口返回结果：{}", body);
        return body;
    }


}
