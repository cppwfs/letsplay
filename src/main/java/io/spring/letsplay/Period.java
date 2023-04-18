
package io.spring.letsplay;

import com.fasterxml.jackson.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "number",
    "name",
    "detailedForecast"
})
public class Period {

    @JsonProperty("number")
    private Integer number;
    @JsonProperty("name")
    private String name;
    @JsonProperty("detailedForecast")
    private String detailedForecast;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("number")
    public Integer getNumber() {
        return number;
    }

    @JsonProperty("number")
    public void setNumber(Integer number) {
        this.number = number;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("detailedForecast")
    public String getDetailedForecast() {
        return detailedForecast;
    }

    @JsonProperty("detailedForecast")
    public void setDetailedForecast(String detailedForecast) {
        this.detailedForecast = detailedForecast;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
