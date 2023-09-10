import scanner.Scanner;
import scanner.Worker;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static List<Integer> portList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(50, 0, 5000);
        scanner.addWorker();
        scanner.portScan();

        try{
            Thread.sleep(1000*60*5);
            System.out.println(scanner.getPortList());
        }catch(Exception e){

        }

    }
}