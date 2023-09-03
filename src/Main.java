import java.net.*;

public class Main {
    public static void main(String[] args) {

        InetAddress addr = null;
        Socket socket = null;

        try {
            addr = InetAddress.getLocalHost();
            System.out.println(addr.getHostAddress());
            for(int p = 130; p<140; p++) {
                try {
                    socket = new Socket("localhost", p);
                    System.out.println("Port " + p + " is open");
                }catch(Exception e) {
                    continue;
                }
                socket.close();


            }
        }catch(Exception e) {
            e.printStackTrace();
        }


    }
}