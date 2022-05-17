package com.eek.model;

import lombok.Getter;

@Getter
public enum TaskDifficulty {

    SURE_THING(1, "Sure thing"),
    WALK_IN_THE_PARK(2, "Walk in the park"),
    PIECE_OF_CAKE(3, "Piece of cake"),
    QUITE_LIKELY(4, "Quite likely"),
    HMMMM(5, "Hmmm...."),
    RISKY(6, "Risky"),
    GAMBLE(7, "Gamble"),
    RATHER_DETRIMENTAL(8, "Rather detrimental"),
    SUICIDE_MISSION(10, "Suicide mission"),
    UNKNOWN_DIFFICULTY(99, "Unknown difficulty");

    private Integer difficulty;
    private String description;

    TaskDifficulty(int difficulty, String description) {
        this.difficulty = difficulty;
        this.description = description;
    }

    public static TaskDifficulty getByDescription(String description) {
        for (TaskDifficulty taskDifficulty : TaskDifficulty.values()) {
            if (description.equals(taskDifficulty.getDescription())) {
                return taskDifficulty;
            }
        }
        return UNKNOWN_DIFFICULTY;
    }
}
