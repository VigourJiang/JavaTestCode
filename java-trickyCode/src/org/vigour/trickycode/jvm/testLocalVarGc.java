package org.vigour.trickycode.jvm;

/**
 * 《深入理解Java虚拟机》第二版的测试代码。
 * jvm参数: -verbose:gc
 */
public class testLocalVarGc {

	// 如果没有x=3的语句，System.gc不会引起placeHolder指向的内存被回收
	// 但是如果test方法被反复调用，导致JIT介入，即使没有x=3，placeHolder也会被gc回收
	private static void test(){
		{
			byte[] placeHolder = new byte[64*1024*1024];
		}
		//int x =3;
		System.gc();
	}

	public static void main(String[] args) {
		// 默认情况下，JVM在Client模式下,方法被反复调用1500次，会引起该方法被JIT编译。
		for(int i = 0; i < 1501; i++){
			test();
		}
	}
}
