package tab.com.au.codetest.data;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum RaceStatus {
    UNKNOWN(""),
    NORMAL("Normal"),
    COMPLETED("Completed"),
    CLOSED("Closed"),
    ABANDONED("Abandoned"),
    SUSPENDED("Suspended"),
    STARTED("Started"),
    PAYING("Paying"),
    INTERIM("Interim"),
    WIN_POOL_CLOSED("WinPoolClosed"),
    AT_LEAST_ONE_POOL_INTERIM("AtLeastOnePoolInterim"),
    ALL_POOL_RESULTED("AllPoolResulted"),
    NOT_ALL_POOL_RESULTED("NotAllPoolResulted"),
    RESULTED("Resulted"),
    RESERVED("Reserved"),
    PROTEST("Protest");

    protected String raceStatus;

    RaceStatus(String raceStatus) {
        this.raceStatus = raceStatus;
    }

    private static final Map<String, RaceStatus> reverseLookupMap = new HashMap<>();

    static {
        for (RaceStatus status : EnumSet.allOf(RaceStatus.class)) {
            reverseLookupMap.put(status.raceStatus.toLowerCase(), status);
        }
    }

    @JsonCreator
    public static RaceStatus forValue(String raceStatus) {
        String key = raceStatus.toLowerCase();
        return reverseLookupMap.get(key) == null ?
                UNKNOWN :
                reverseLookupMap.get(key);
    }

    @Override
    public String toString() {
        return raceStatus;
    }

}
