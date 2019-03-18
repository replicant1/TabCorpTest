package tab.com.au.codetest.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Meeting implements Serializable {

    @JsonProperty("meetingName")
    protected String name;

    @JsonProperty
    protected RaceType raceType;

    @JsonProperty
    protected String location;

    @JsonProperty
    protected String weatherCondition;

    public String getName() {
        return name;
    }

    public RaceType getRaceType() {
        return raceType;
    }

    public String getLocation() {
        return location;
    }

    public String getWeatherCondition() {
        return weatherCondition;
    }
}
