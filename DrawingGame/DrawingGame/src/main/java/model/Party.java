package model;

public class Party {
    Player shortest;
    Player puller;

    public void setShortest(Player shortest) {
        this.shortest = shortest;
    }

    public Player getShortest() {
        return shortest;
    }

    public Player getPuller() {
        return puller;
    }

    public void setPuller(Player puller) {
        this.puller = puller;
    }
}
