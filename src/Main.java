import scanner.Scanner;
import scanner.Worker;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static List<Integer> portList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(5, 100, 500);
//        scanner.setWaitingWorker();
        scanner.addWorker();
        scanner.portScan();

        System.out.println(scanner.getPortList());
    }
}