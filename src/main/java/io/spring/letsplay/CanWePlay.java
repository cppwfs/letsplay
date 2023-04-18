package io.spring.letsplay;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Function;

public class CanWePlay implements Function<String, String> {

    private String getZone(String url) {
        int  lastIndex = url.lastIndexOf("/");
        return (lastIndex < 0) ? null : url.substring(lastIndex);
    }

    /**
     * Accepts a comma delimited sets of latitude and longitude and returns a simple string stating whether it's time to play or not.
     * @param coordinates Comma delimited string containing latitude and longitude.
     * @return string containing "Let's Play" if it is sunny or clear else "Not Today".  If an exception occurs it will return "The Coordinates did not return a place we can get a forecast.  Sorry :-(".
     */
    @Override
    public String apply(String coordinates) {
        RestTemplate template = new RestTemplate();
        String result = null;
        try {
            result = template.getForObject("https://api.weather.gov/points/" + coordinates, String.class);
        } catch (HttpClientErrorException exception) {
            return "The Coordinates did not return a place we can get a forecast.  Sorry :-(";
        }
//        System.out.println(result);
        ObjectMapper mapper = new ObjectMapper() ;
        NoaaZone zone = null;
        try {
            zone = mapper.readValue(result, NoaaZone.class);
        } catch (JsonProcessingException e) {
            return "The Coordinates did not return a place we can get a forecast.  Sorry :-(";
        }
//        System.out.println(zone.getForecastZone());
//        System.out.println(zone.getAdditionalProperties().get("properties"));
        LinkedHashMap properties = (LinkedHashMap) zone.getAdditionalProperties().get("properties");
        System.out.println(properties.get("forecastZone"));
        result = template.getForObject("https://api.weather.gov/zones/county/" +
                getZone((String)properties.get("forecastZone")) +
                "/forecast", String.class);
//        System.out.println(result);
        NoaaForecast forecast = null;
        try {
            forecast = mapper.readValue(result, NoaaForecast.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        List<Period> periods = forecast.getProperties().getPeriods();
        System.out.println(periods.get(0).getDetailedForecast());
        String detailedForecast = periods.get(0).getDetailedForecast().toUpperCase();
        return (detailedForecast.contains("SUNNY") ||
                detailedForecast.contains("CLEAR")) ? "Let's Play" : "Not Today";
    }
}
