package scanner;

import common.core.ScanningObserver;
import observe.WorkerObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Scanner {

    private final List<Integer> OPENED_PORT_LIST = new ArrayList<>();
    private final List<Worker> WORKER_LIST = new ArrayList<>();
    private final int DEFAULT_WORKER_CNT = 2;
    private int WORKER_CNT = DEFAULT_WORKER_CNT;

    private final int DEFAULT_START_PORT = 0;
    private final int DEFAULT_END_PORT = 65535;

    private int START_PORT = DEFAULT_START_PORT;
    private int END_PORT = DEFAULT_END_PORT;

    private ScanningObserver observer = null;
    private boolean IS_WAITING = false;

    public Scanner() {
        observer = new WorkerObserver();
    }

    public Scanner(int workerCounts) {
        if(workerCounts == 0) {
            throw new RuntimeException("0 is not proper arguments");
        }
        observer = new WorkerObserver();
        WORKER_CNT = workerCounts;
    }

    public Scanner(int start, int end) {
        if(start > end) {
            throw new RuntimeException("invalid scan range");
        }
        observer = new WorkerObserver();
        START_PORT = start;
        END_PORT = end;
    }

    public Scanner(int workerCounts, int start, int end) {
        this(start, end);
        if(workerCounts > end-start) {
            throw new RuntimeException("do not set worker counts over scanning range");
        }
        WORKER_CNT = workerCounts;
    }


    public void setWaitingWorker() {
        IS_WAITING = true;
    }

    public void addWorker() {

        Worker worker = null;
        final int RANGE = END_PORT - START_PORT;
        final int SEQ = RANGE / WORKER_CNT;
        final int REST_PORT_CNT = RANGE - (SEQ*WORKER_CNT);
        for(int i = 0; i<WORKER_CNT; i++) {
            worker = new Worker(observer);
            worker.setRange(
                    i == 0 ? START_PORT : START_PORT+1,
                    i != WORKER_CNT-1 ? START_PORT+=SEQ : (START_PORT+=SEQ)+REST_PORT_CNT
            );
            WORKER_LIST.add(worker);
        }
    }

    public void portScan() {
        WORKER_LIST.forEach(Worker -> {
            Worker.startWork();
            if(IS_WAITING) {
                try {
                    Worker.waiting();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void showEveryWorkerRange() {
        WORKER_LIST.forEach(Worker -> {
            Worker.showRange();
        });
    }

    public List<Integer> getPortList() {
        return observer.getPortList()
                .stream()
                .sorted()
                .collect(Collectors.toList());
    }

}
