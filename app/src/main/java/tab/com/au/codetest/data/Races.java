package tab.com.au.codetest.data;

import android.util.Log;

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

    @Override
    public String toString() {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < races.size(); i++) {
            buf.append(races.get(i) + "\n");
        }
        return buf.toString();
    }
}
