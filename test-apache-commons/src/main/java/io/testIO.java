package io;

import org.apache.commons.io.EndianUtils;
import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.File;

/**
 * Created by vigour on 2014-8-14.
 */
public class testIO {
    public static void main(String[] args) throws Exception {
        File directory = new File(new File("."), "src");
        FileAlterationObserver observer = new FileAlterationObserver(directory);
        observer.addListener(new FileAlterationListener() {

            @Override
            public void onStart(FileAlterationObserver observer) {
                System.out.println("onStart");
            }

            @Override
            public void onDirectoryCreate(File directory) {
                System.out.println("onDirectoryCreate: " + directory.getName());
            }

            @Override
            public void onDirectoryChange(File directory) {
                System.out.println("onDirectoryChange: " + directory.getName());
            }

            @Override
            public void onDirectoryDelete(File directory) {
                System.out.println("onDirectoryDelete: " + directory.getName());
            }

            @Override
            public void onFileCreate(File file) {
                System.out.println("onFileCreate: " + file.getName());
            }

            @Override
            public void onFileChange(File file) {
                System.out.println("onFileChange: " + file.getName());
            }

            @Override
            public void onFileDelete(File file) {
                System.out.println("onFileDelete: " + file.getName());
            }

            @Override
            public void onStop(FileAlterationObserver observer) {
                System.out.println("onStop");
            }
        });

        long interval = 1000;
        FileAlterationMonitor monitor = new FileAlterationMonitor(interval);
        monitor.addObserver(observer);
        monitor.start();
        Thread.sleep(100000);
        monitor.stop();
    }
}
