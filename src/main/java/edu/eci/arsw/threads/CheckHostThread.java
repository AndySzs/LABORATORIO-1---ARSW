package edu.eci.arsw.threads;

import edu.eci.arsw.spamkeywordsdatasource.HostBlacklistsDataSourceFacade;
import java.util.LinkedList;
import java.util.List;

public class CheckHostThread extends Thread {

    private final int startIndex;
    private final int endIndex;
    private final String ipaddress;
    private final HostBlacklistsDataSourceFacade skds;
    private final List<Integer> ocurrences = new LinkedList<>();

    public CheckHostThread(int startIndex, int endIndex, String ipaddress) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.ipaddress = ipaddress;
        this.skds = HostBlacklistsDataSourceFacade.getInstance();
    }

    @Override
    public void run() {
        for (int i = startIndex; i < endIndex; i++) {
            if (skds.isInBlackListServer(i, ipaddress)) {
                ocurrences.add(i);
            }
        }
    }

    public int getOcurrencesCount() {
        return ocurrences.size();
    }

    public List<Integer> getOcurrences() {
        return ocurrences;
    }
}