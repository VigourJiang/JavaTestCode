package org.vigour.trickycode;

import sun.rmi.runtime.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 控制台处理工具箱
 *
 * @author leizhimin 2009-6-25 14:12:14
 */
public final class CmdToolkit {
    private CmdToolkit() {
    }

    /**
     * 读取控制命令的输出结果
     *
     * @param cmd                命令
     * @return 控制命令的输出结果
     * @throws IOException
     */
    public static String readConsole(String cmd) throws IOException, InterruptedException {
        StringBuilder cmdout = new StringBuilder();
        System.out.println("执行命令：" + cmd);
        Process process = Runtime.getRuntime().exec(cmd);     //执行一个系统命令
        process.waitFor();
        InputStream fis = process.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        String line;
        while ((line = br.readLine()) != null) {
            cmdout.append(line).append(System.getProperty("line.separator"));
        }
        System.out.println("执行系统命令后的结果为：\n" + cmdout.toString());
        return cmdout.toString().trim();
    }

    public static void useProcessBuilder(){
        try {
            ProcessBuilder proc = new ProcessBuilder("notepad.exe", "testfile");
            proc.start();
        } catch (Exception e) {
            System.out.println("Error executing notepad.");
        }
    }
    public static void main(String[] args) throws IOException, InterruptedException {
        readConsole("sc query tomcat");
        useProcessBuilder();
    }
}