package com.epam.configuration;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;

import static java.lang.String.format;

@Slf4j
public class AddressConfiguration {
    private static final String KILL_PROCESS_COMMAND = "taskkill /PID %s /F";
    private static final String FIND_PID_COMMAND = "cmd /c netstat -ano | findstr %s";

    public static void makePortAvailableIfOccupied(final int port) {
        if (!isPortFree(port)) {
            try {
                String PID = getPID(port);
                Runtime.getRuntime().exec(format(KILL_PROCESS_COMMAND, PID));
            } catch (IOException e) {
                log.warn("Couldn't execute runtime command, message: {}", e.getMessage());
            }
        }
    }

    private static String getPID(final int port) {
        String PID = "";
        Runtime rt = Runtime.getRuntime();
        try {
            Process process = rt.exec(format(FIND_PID_COMMAND, port));
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String string = stdInput.readLine();
            if (string != null) {
                int index = string.lastIndexOf(" ");
                PID = string.substring(index);
                log.info("PID: {}", PID);
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return PID;
    }

    private static boolean isPortFree(final int port) {
        boolean isFree = true;
        try (ServerSocket ignored = new ServerSocket(port)) {
            log.info("Specified port - {} is available and ready to use", port);
        } catch (Exception e) {
            isFree = false;
            log.warn("Specified port - {} is occupied by some process, process will be terminated", port);
        }
        return isFree;
    }
}
