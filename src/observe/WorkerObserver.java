package observe;

import common.core.ScanningObserver;

import java.util.ArrayList;
import java.util.List;

public class WorkerObserver implements ScanningObserver {

    private List<Integer> OPENED_PORT_LIST = new ArrayList<>();

    @Override
    public void successScan(int port) {
        OPENED_PORT_LIST.add(port);
    }

    @Override
    public List<Integer> getPortList() {
        return OPENED_PORT_LIST;
    }
}
