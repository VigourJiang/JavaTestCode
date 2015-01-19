package org.vigour.trickycode.concurrency;

import java.io.File;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcurrentLineCount {
    private final ExecutorService executor;

    ConcurrentLineCount(ExecutorService executor) {
        this.executor = executor;
    }

    int countLine(final File root) {
        final CompletionService<Integer> completionService =
            new ExecutorCompletionService<Integer>(executor);

        final Thread mainThread = Thread.currentThread();
        executor.submit(new Runnable() {

            @Override
            public void run() {
                traversal(root, 0);
                finished = true;
                mainThread.interrupt();
            }

            private void traversal(File file, int level) {
                if (level >= 4)
                    return;
                File[] files = file.listFiles();
                for (File f : files) {
                    // 判断是否为文件夹
                    if (f.isDirectory()) {
                        traversal(f, level + 1);
                        //System.out.println(f.getAbsolutePath());
                    } else {
                        fileCount.incrementAndGet();
                        completionService.submit(new Callable<Integer>() {
                            public Integer call() {
                                return 1;
                            }
                        });

                    }
                }
            }

        });
        int count = 0;
        while (true) {
            if (finished && fileCount.get() == 0)
                break;
            Future<Integer> future = null;
            try {
                future = completionService.take();
            } catch (InterruptedException e) {
            }
            try {
                if (future != null) {
                    fileCount.decrementAndGet();
                    count += future.get().intValue();
                } else {
                    System.out.println("");
                }
            } catch (InterruptedException e) {
            } catch (ExecutionException e) {
            }
        }
        executor.shutdown();
        return count;
    }

    private volatile boolean searchFinished;
    private LinkedBlockingQueue<File> foundFiles =
        new LinkedBlockingQueue<File>();
    private volatile AtomicInteger fileCount = new AtomicInteger();
    private volatile boolean finished = false;

    public static void main(String[] args) {
        ConcurrentLineCount lc =
            new ConcurrentLineCount(Executors.newCachedThreadPool());
        int count = lc.countLine(new File("F:\\java_libs"));
        System.out.println(count);
        
    }
}
