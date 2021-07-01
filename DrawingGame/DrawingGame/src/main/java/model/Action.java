package model;

public enum Action {
    REQUEST_CHAIR,     // salje klijent
    PULL_STICK,        // salje server
    GUESS_OUTCOME,     // salje server
    PULL,              // salje klijent
    YES,               // pretpostavka da je taj izvukao, salje je klijent
    NO,                // pretpostavka da je taj nije izvukao, salje je klijent
    STOP,
    PLAY_AGAIN
}
