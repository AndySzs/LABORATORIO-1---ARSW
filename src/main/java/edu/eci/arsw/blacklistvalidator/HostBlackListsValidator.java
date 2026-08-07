package edu.eci.arsw.blacklistvalidator;

import edu.eci.arsw.spamkeywordsdatasource.HostBlacklistsDataSourceFacade;
import edu.eci.arsw.threads.CheckHostThread;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HostBlackListsValidator {

    private static final int BLACK_LIST_ALARM_COUNT = 5;

    public List<Integer> checkHost(String ipaddress, int n) {

        LinkedList<Integer> blackListOcurrences = new LinkedList<>();
        HostBlacklistsDataSourceFacade skds = HostBlacklistsDataSourceFacade.getInstance();
        int totalServers = skds.getRegisteredServersCount();

        int baseSize = totalServers / n;
        int remainder = totalServers % n;

        List<CheckHostThread> threads = new LinkedList<>();

        int start = 0;
        for (int i = 0; i < n; i++) {
            int extra = (i < remainder) ? 1 : 0;
            int end = start + baseSize + extra;

            CheckHostThread t = new CheckHostThread(start, end, ipaddress);
            threads.add(t);
            t.start();

            start = end;
        }

        for (CheckHostThread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        int ocurrencesCount = 0;
        for (CheckHostThread t : threads) {
            ocurrencesCount += t.getOcurrencesCount();
            blackListOcurrences.addAll(t.getOcurrences());
        }

        if (ocurrencesCount >= BLACK_LIST_ALARM_COUNT) {
            skds.reportAsNotTrustworthy(ipaddress);
        } else {
            skds.reportAsTrustworthy(ipaddress);
        }

        LOG.log(Level.INFO, "Checked Black Lists:{0} of {1}",
                new Object[]{totalServers, totalServers});

        return blackListOcurrences;
    }

    private static final Logger LOG = Logger.getLogger(HostBlackListsValidator.class.getName());
}