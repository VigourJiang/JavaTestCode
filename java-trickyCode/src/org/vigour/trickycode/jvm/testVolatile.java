package org.vigour.trickycode.jvm;

/**
 * User: Jiang Fuqiang<br>
 * Date: 2015-1-28<br>
 * 通过打印JVM的JIT产生的汇编指令，验证volatile关键字的功能。<br>
 * 设置jvm打印汇编指令的方法：<br>
 * 1. 把hsdis-i386.dll复制到jdk目录的server和client目录下（和jvm.dll同目录）<br>
 * 2. 加上以下jvm参数  -XX:+UnlockDiagnosticVMOptions   -XX:+PrintAssembly<br>
 * 反汇编结果：<br>
 * 1. 对于read的操作，对x变量加不加volatile修饰符，产生的汇编指令都是一样的<br>
 * 2. 对于write操作，见下文注释
 */
public class testVolatile {
    volatile static int x = 3;

    private static void rw() {
        x = x + 4;
    }

    private static int readVolatile() {
        return x;
    }

    private static void writeVolatile(int val) {
        /* 以下是当x不是volatile时，输出的汇编指令
        0x024b1c8b: mov    $0x246f7808,%esi    ; 获取x的地址
        0x024b1c90: movl   $0x8324,0x70(%esi)  ; 向x的地址中，写入数值。
        */
        /* 以下是当x为volatile时，输出的汇编指令
        0x02831c8b: mov    $0x246f7808,%esi   ; 获取x的地址
        0x02831c90: mov    $0x8324,%edi
        0x02831c95: mov    %edi,0x70(%esi)    ; 向x的地址中，写入数值。
        0x02831c98: lock addl $0x0,(%esp)     ; 加入一个Memory Fence
        */
        x = 0x8324;
    }

    public static void main(String... args) {
        for (int i = 0; i < 1000000; i++) {// trigger JIT
            readVolatile();
            writeVolatile(i);
            rw();
        }
    }
}
