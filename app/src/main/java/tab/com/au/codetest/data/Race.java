package tab.com.au.codetest.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.joda.time.DateTime;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Race implements Serializable {

    @JsonProperty("raceNumber")
    protected int number;

    protected Meeting meeting;

    @JsonProperty
    protected String raceName;

    @JsonProperty
    protected DateTime raceStartTime;

    public int getNumber() {
        return number;
    }

    public Meeting getMeeting() {
        return meeting;
    }

    public String getRaceName() {
        return raceName;
    }

    public DateTime getRaceStartTime() {
        return raceStartTime;
    }

    @Override
    public String toString() {
        StringBuffer buf =new StringBuffer();
        buf.append("(number=" + number);
        buf.append(",name=" + raceName);
        buf.append(",start=" + raceStartTime);
        buf.append(",meeting=" + meeting + ")");
        return buf.toString();
    }
}
