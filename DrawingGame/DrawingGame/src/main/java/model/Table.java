package model;

import java.util.Random;

public class Table {

    private Player[] players;
    private Party party;
    private int shortest;
    private int puller;
    private int brojac;
    private int zavrsili;
    private boolean pocela = false;
    public Table() {
        this.players = new Player[6];
        brojac = 0;
        zavrsili = 0;
        party = new Party();
    }


    public synchronized boolean giveSeat(Player player) {
        for (int i = 0; i < 6 ; i++) {
            if(players[i] == null) {
                System.out.println("Novi igrac seda za sto");
                players[i] = player;
                if (i == 5 && !pocela){
                    pocela = true;
                    Random r= new Random();
                    int br = r.nextInt(6);
                    System.out.println(br);
                    shortest = br; // br
                    puller = 0;
//                    party.setShortest(players[br]);
//                    party.setPuller(players[0]);
                }
                return true;
            }
        }
        System.out.println("Ne moze da udje");

        return false;
    }

    public synchronized void newRound(){
        System.out.println("Nova runda !");
        brojac++;
     //   if (brojac < 6){
        puller = brojac;
           // party.setPuller(players[brojac]);
//            Random r= new Random();
//            int br = r.nextInt(6 - brojac) + brojac;
//            party.setShortest(players[br]);
    //    }

    }

    public synchronized void newParty(Player p){
        ispisiRezultat();
        brojac = 0;

        Random r= new Random();
        int br = r.nextInt(6);
        System.out.println(br);
        shortest = br;
        puller = 0;

        for (int i =0 ;i < players.length; i++){
            if (players[i].equals(p)){
                players[i] = null;
                if (players[i] ==null){
                    System.out.println("Uspesno izbacen igrac");
                }
                System.out.println("Izbacen igrac");
            }
        }


//        party.setShortest(players[br]);
//        party.setPuller(players[0]);


    }

    public int getShortest() {
        return shortest;
    }

    public int getPuller() {
        return puller;
    }

    public Party getParty() {
        return party;
    }

    public int getBrojac() {
        return brojac;
    }

    public void ispisiRezultat(){
        int max = 0;
        Player winner = null;
        for(Player p : players){
            if (p.getPoints() > max){
                max = p.getPoints();
                winner = p;
            }
        }

        System.out.println("--------------------------------------------");
        System.out.println("Igrac " + winner +" je pobedio sa " + winner.getPoints() +" poena");
        System.out.println("--------------------------------------------");
    }

    public void isprazniNiz(){
        players = null;
    }

    public Player[] getPlayers() {
        return players;
    }
}
