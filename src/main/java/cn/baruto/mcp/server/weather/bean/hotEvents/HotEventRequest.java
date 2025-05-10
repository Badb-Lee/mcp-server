package cn.baruto.mcp.server.weather.bean.hotEvents;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import lombok.Data;


@Data
public class HotEventRequest {

    @JsonProperty(required = true,value = "hotEventName")
    @JsonPropertyDescription("热点事件的名字")
    private String hotEventName;


    @JsonProperty(required = true,value = "num")
    @JsonPropertyDescription("热点事件数量")
    private int num;


}
