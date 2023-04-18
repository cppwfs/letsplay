package io.spring.letsplay;


import com.fasterxml.jackson.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "city",
        "state",
        "distance",
        "bearing"
})
public class RelativeLocation {

    @JsonProperty("city")
    private Object city;
    @JsonProperty("state")
    private Object state;
    @JsonProperty("distance")
    private Object distance;
    @JsonProperty("bearing")
    private Object bearing;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("city")
    public Object getCity() {
        return city;
    }

    @JsonProperty("city")
    public void setCity(Object city) {
        this.city = city;
    }

    @JsonProperty("state")
    public Object getState() {
        return state;
    }

    @JsonProperty("state")
    public void setState(Object state) {
        this.state = state;
    }

    @JsonProperty("distance")
    public Object getDistance() {
        return distance;
    }

    @JsonProperty("distance")
    public void setDistance(Object distance) {
        this.distance = distance;
    }

    @JsonProperty("bearing")
    public Object getBearing() {
        return bearing;
    }

    @JsonProperty("bearing")
    public void setBearing(Object bearing) {
        this.bearing = bearing;
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

