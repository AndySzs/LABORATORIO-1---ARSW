package edu.eci.arsw.blacklistvalidator;

import java.util.List;

public class PerformanceMain {

    public static void main(String[] args) {

        String ip = "202.24.34.55";
        int cores = Runtime.getRuntime().availableProcessors();

        int[] threadCounts = {1, cores, cores * 2, 50, 100};

        System.out.println("Núcleos detectados: " + cores);
        System.out.println("IP a analizar: " + ip);
        System.out.println();

        for (int n : threadCounts) {
            HostBlackListsValidator hblv = new HostBlackListsValidator();

            long startTime = System.nanoTime();
            List<Integer> result = hblv.checkHost(ip, n);
            long endTime = System.nanoTime();

            double millis = (endTime - startTime) / 1_000_000.0;

            System.out.printf("Hilos: %-4d  Tiempo: %.2f ms  Ocurrencias: %d%n",
                    n, millis, result.size());
        }
    }
}