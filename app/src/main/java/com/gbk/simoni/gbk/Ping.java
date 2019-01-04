package com.gbk.simoni.gbk;

import java.util.TimerTask;

public class Ping  extends TimerTask {

    @Override
    public void run() {
        System.out.println(" --> scheduled ping ");
    }
}
