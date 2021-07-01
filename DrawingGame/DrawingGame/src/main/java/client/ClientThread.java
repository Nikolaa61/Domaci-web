package client;

import com.google.gson.Gson;
import model.Action;
import model.Request;
import model.Response;
import model.Result;

import java.io.*;
import java.net.Socket;
import java.util.Random;
import java.util.UUID;

public class ClientThread implements Runnable{

    private static final int PORT = 9999;

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    private Gson gson;

    public ClientThread() throws IOException {
        socket = new Socket("localhost", PORT);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
        gson = new Gson();
    }

    public void run() {
        Request request = new Request();
        UUID id = UUID.randomUUID();

        System.out.println("Igrac " + id.toString() + " pokusava da pristupi igri.");

        request.setId(id);
        request.setAction(Action.REQUEST_CHAIR);
        sendRequest(request);

        Response response = receiveResponse();
        if (response.getResult() == Result.FAILURE){
            System.out.println("Igrac " + id.toString() + " nije uspeo da se prikljuci igri.");
        }else{
            System.out.println("Igrac " + id.toString() + " je uspeo da se prikljuci igri.");
            while(response.getAkcija() != Action.STOP){
                System.out.println("USAO");
                if (response.getAkcija() == Action.PLAY_AGAIN){
                    response = receiveResponse();
                }
                if (response.getAkcija() == Action.PULL_STICK){
                    request.setAction(Action.PULL);
                }else if (response.getAkcija() == Action.GUESS_OUTCOME){
                    Random r = new Random();
                    int br = r.nextInt(2);
                    if (br == 0){
                        request.setAction(Action.YES);
                    }else{
                        request.setAction(Action.NO);
                    }
                }
                sendRequest(request);
                response = receiveResponse();
                System.out.println(response.getAkcija());
            }

            System.out.println("Zavrsio");

        }


    }

    public void sendRequest(Request request) {
        out.println(gson.toJson(request));
    }

    public Response receiveResponse() {
        try {
            return gson.fromJson(in.readLine(), Response.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
