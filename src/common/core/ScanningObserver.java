package common.core;

import java.util.List;

public interface ScanningObserver {

    public void successScan(int port);

    public List<Integer> getPortList();

}
