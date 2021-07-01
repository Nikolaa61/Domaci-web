package client;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ClientMain {

    public static void main(String[] args) throws IOException, InterruptedException {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(60);

        for (int i = 0; i < 60; i++) {
            Random r= new Random();
            double interval = r.nextDouble();

            scheduledExecutorService.schedule(new ClientThread(), (long) interval, TimeUnit.SECONDS);
            if ((i+1) % 6 == 0){
                Thread.sleep(5000);
            }
        }

        scheduledExecutorService.shutdown();
    }
}
