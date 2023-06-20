package org.launchcode.qleanquotes.models.enums;

public enum CleaningOption {
    AVERAGE("Maintenance Clean"),
    DEEP("Deep Clean");

    private final String displayName;

    CleaningOption(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
