package scanner;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Worker {

    private final List<Integer> PORT_LIST = new ArrayList<>();
    private int START_PORT;
    private int END_PORT;
    private Thread worker;

    private Socket socket = null;

    public Worker() {

    }

    public void setRange(int start, int end) {
        START_PORT = start;
        END_PORT = end;
    }

    public void work()  {
        if(isInValidScanningPortNumber()) {
            throw new RuntimeException("Invalid Port Number");
        }
        for(int port=START_PORT; port<=END_PORT; port++) {
            try {
                System.out.println(worker.getName() + ": " + port);
                socket = new Socket("localhost", port);
                PORT_LIST.add(port);
                socket.close();
            }catch(Exception e){
                continue;
            }

        }
    }

    public void waiting() throws InterruptedException {
        worker.join();
    }

    public void showRange() {
        System.out.println("=====================================");
        System.out.println("START_PORT: " + START_PORT);
        System.out.println("END_PORT: " + END_PORT);
    }

    public void startWork() {
        setWork();
        if(worker == null) {
            throw new RuntimeException("Not Yet Created Thread!");
        }
        worker.start();
    }

    public List<Integer> getPortList() {
        return PORT_LIST;
    }

    private void setWork() {
        Runnable run = new Thread(() -> work());
        worker = new Thread(run);
    }

    private boolean isInValidScanningPortNumber() {
        if(START_PORT < 0 || START_PORT > 65535) {
            return true;
        }
        if(END_PORT < 0 || END_PORT > 65535) {
            return true;
        }
        return false;
    }
}
