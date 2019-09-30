package com.example.macapp.server.mvp.model;


import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.util.HashSet;
import java.util.Set;

class GetConnectedDevicesTask extends AsyncTask<ResultWraper, Void, Set<Long>> {

    private static int HEX_RADIX = 16;

    private ResultWraper resultWraper;

    @Override
    protected Set<Long> doInBackground(ResultWraper... resultWrapers) {
        resultWraper = resultWrapers[0];
        int reachableTimeout = 1000;
        BufferedReader br;
        Set<Long> set = new HashSet<>();

        try {
            br = new BufferedReader(new FileReader("/proc/net/arp"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] splitted = line.split(" +");
                String mac = splitted[3];
                if (mac.matches("..:..:..:..:..:..")) {
                    boolean isReachable = InetAddress.getByName(splitted[0]).isReachable(reachableTimeout);
                    if (isReachable) {
                        mac = mac.replace(":", "");
                        Long macLong = Long.parseLong(mac, HEX_RADIX);
                        set.add(macLong);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return set;
    }

    @Override
    protected void onPostExecute(Set<Long> set) {
        super.onPostExecute(set);

        resultWraper.deliverResult(set);
    }
}
