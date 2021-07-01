package server;

import model.Table;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerMain {

    private static final int PORT = 9999;


    public static void main(String[] args) {

        Table table = new Table();
        CountDownLatch countDownLatch = new CountDownLatch(6);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(6, new Runnable() {

            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Barijera je pukla posao, niti su pocele da rade.");
            }
        });

        CyclicBarrier zavrsiPogadjanje = new CyclicBarrier(6, new Runnable() {

            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //System.out.println("Barijera je pukla posao, niti su pocele da rade.");
            }
        });

        CyclicBarrier pogadjajte = new CyclicBarrier(6, new Runnable() {

            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //System.out.println("Barijera je pukla posao, niti su pocele da rade.");
            }
        });

        try {
            ServerSocket ss = new ServerSocket(PORT);
            ExecutorService executorService = Executors.newCachedThreadPool();
            System.out.println("Krupije je spreman");

            while(true) {
                Socket socket = ss.accept();
                executorService.submit(new ServerThread(socket, table, countDownLatch, cyclicBarrier, zavrsiPogadjanje, pogadjajte));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
