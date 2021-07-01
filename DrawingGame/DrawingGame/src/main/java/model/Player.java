package model;

import java.util.UUID;

public class Player {

    private UUID id;
    private int points;

    public Player(UUID id) {
        this.id = id;
    }

    public int getPoints() {
        return points;
    }

    public void addPoint() {
        points++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return id.equals(player.id);
    }

    @Override
    public String toString() {
        return  id+"";
    }
}
