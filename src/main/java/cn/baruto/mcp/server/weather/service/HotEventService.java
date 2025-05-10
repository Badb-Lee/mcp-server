package cn.baruto.mcp.server.weather.service;

import cn.baruto.mcp.server.weather.bean.hotEvents.HotEventRequest;
import cn.baruto.mcp.server.weather.bean.weather.WeatherFunctionRequest;
import cn.baruto.mcp.server.weather.properties.HotEventApiProperties;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import jakarta.annotation.Resource;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class HotEventService {

    @Resource
    private HotEventApiProperties hotEventApiProperties;


    @Tool
    public String getHotEvents(HotEventRequest request){
        String hotEventName = request.getHotEventName();
        log.info("获取{}相关的热点事件",hotEventName);
        String url = "https://google.serper.dev/news";

        @Cleanup
        HttpResponse response = HttpUtil.createGet(url)
                .header("X-API-KEY", hotEventApiProperties.getApikey())
                .header("Content-Type", "application/json")
                .form("q", hotEventName)
                .form("num",request.getNum())
                .execute();
        String body = response.body();
        log.info("查询接口返回结果{}",body);
        return body;
    }



}
