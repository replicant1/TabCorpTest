package tab.com.au.codetest.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Races implements Serializable {

    @JsonProperty
    protected List<Race> races;

    public List<Race> getRaces() {
        return races;
    }
}
