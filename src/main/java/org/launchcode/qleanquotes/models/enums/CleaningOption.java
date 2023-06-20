package org.launchcode.qleanquotes.models.enums;

public enum CleaningOption {
    AVERAGE("Average Cleaning"),
    DEEP("Deep Cleaning");

    private final String displayName;

    CleaningOption(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
