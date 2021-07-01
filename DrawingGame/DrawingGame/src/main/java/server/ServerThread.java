package server;

import com.google.gson.Gson;
import model.*;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class ServerThread extends Thread {

    Socket socket;
    BufferedReader in;
    PrintWriter out;
    CountDownLatch countDownLatch;
    CyclicBarrier cyclicBarrier;
    CyclicBarrier zavrsiPogadjanje;
    CyclicBarrier pogadjajte;
    private Gson gson;
    private Table table;

    public ServerThread(Socket socket, Table table, CountDownLatch countDownLatch, CyclicBarrier cyclicBarrier, CyclicBarrier zavrsiPogadjanje, CyclicBarrier pogadjajte) {
        this.socket = socket;
        this.table = table;
        this.countDownLatch = countDownLatch;
        this.cyclicBarrier = cyclicBarrier;
        this.zavrsiPogadjanje = zavrsiPogadjanje;
        this.pogadjajte = pogadjajte;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        gson = new Gson();
    }

    public void run() {

        try {
            Request request = receiveRequest();

            Player player = new Player(request.getId());

            Response response = new Response();
            response.setResult(Result.FAILURE);


            if (request.getAction() == Action.REQUEST_CHAIR) {
                if (table.giveSeat(player)) {
                    response.setResult(Result.SUCCESS);
                    countDownLatch.countDown(); // moze da se desi da uleti puller i deadlock se desi
                    countDownLatch.await();
//                    System.out.println("Usao countDownLatch");
                }else{
                    sendResponse(response); // FAIL
                }


                if (response.getResult() != Result.FAILURE) {
                    // ako nije failure onda je naredba da izvuce ili da pogodi
                    int brojac = 0;
                    boolean flag = true;
                    while(flag){
//                        System.out.println("Cekaju se svi igraci");
                        cyclicBarrier.await(); // moramo da sacekamo da svi dodju jer ce druga nit u novoj rundi
                                               // biti puller a ona ce da prosisa i uci ce u ove sto pogadjaju
                                               // i tu je dedlok.
//                        System.out.println("Usao posle cyclicBarrier");
                        Player []niz = table.getPlayers();
                        if (niz[table.getPuller()].equals(player)) {
//                            System.out.println("Puller");
                            response.setAkcija(Action.PULL_STICK);
                            sendResponse(response);

                            request = receiveRequest(); // nebitan odgovor od klijenta ovde jer je klijent puller
                            pogadjajte.await();
//                            System.out.println("Puller prosao pogadjajte.await()");

                            if (niz[table.getShortest()].equals(player)) {
//                                System.out.println("USAO");
                                //najkraci je, zavrsi partiju
                                flag = false;
                                response.setAkcija(Action.STOP);
                                sendResponse(response);
                                System.out.println("Gotovo");

                                zavrsiPogadjanje.await();
//                                System.out.println("Puller prosao zavrsiPogadjanje.await()");
                                table.newParty(player);

                            } else {
                                //nije najkraci produzi dalje
                                response.setAkcija(Action.PLAY_AGAIN);
                                sendResponse(response);
                                zavrsiPogadjanje.await();
//                                System.out.println("Puller prosao zavrsiPogadjanje.await()");
                                table.newRound();
                                // moraju da zavrse pogadjanje pre nego sto ovaj promeni rundu ili partiju ili await uradi

                            }
                        }else {
                          // ceka da player povuce stick
                            pogadjajte.await();
//                            System.out.println("Pogadjac prosao pogadjajte.await()");
                            response.setAkcija(Action.GUESS_OUTCOME);
                            sendResponse(response);
                            request = receiveRequest();

                            if(request.getAction() == Action.YES){
                                if (niz[table.getPuller()].equals(niz[table.getShortest()])){
                                    player.addPoint();
                                }
                            }else{
                                if (!niz[table.getPuller()].equals(niz[table.getShortest()])){
                                    player.addPoint();
                                }
                            }
                            response.setAkcija(Action.PLAY_AGAIN);
                            zavrsiPogadjanje.await();
//                            System.out.println("Pogadjac prosao zavrsiPogadjanje.await()");

                        }


                    }



                }




            }

            // TODO

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Request receiveRequest() throws IOException {
        return gson.fromJson(in.readLine(), Request.class);
    }

    private void sendResponse(Response response) {
        out.println(gson.toJson(response));
    }
}
