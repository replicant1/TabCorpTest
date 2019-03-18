package tab.com.au.codetest.data;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum RaceType {
    UNKNOWN("", ""),
    THOROUGHBRED("R", "Races"),
    GREYHOUND("G", "Greyhounds"),
    HARNESS("H", "Harness");

    private String mCode;

    private String mLabel;

    RaceType(String code, String label) {
        mCode = code;
        mLabel = label;
    }

    public String code() {
        return mCode;
    }

    @Override
    public String toString() {
        return mLabel;
    }

    @JsonCreator
    public static RaceType fromCode(String code) {
        if ("R".equalsIgnoreCase(code)) {
            return THOROUGHBRED;
        } else if ("G".equalsIgnoreCase(code)) {
            return GREYHOUND;
        } else if ("H".equalsIgnoreCase(code)) {
            return HARNESS;
        } else {
            return UNKNOWN;
        }
    }
}
