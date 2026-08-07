package edu.eci.arsw.blacklistvalidator;

import java.util.List;

public class Main {

    public static void main(String a[]) {
        HostBlackListsValidator hblv = new HostBlackListsValidator();
        int n = 4;

        List<Integer> blackListOcurrences = hblv.checkHost("212.24.24.55", n);
        System.out.println("The host was found in the following blacklists:" + blackListOcurrences);
    }
}