package testOpenJDK;

/**
 * Created by vigour on 2014-8-9.
 */
public class TestMemory {
    public static void main(String args[]) throws InterruptedException {
        Node1 n1 = new Node1();
        n1.left = 0x11111111;
        n1.right = 0x22222222;
        n1.value = 0xaaaaaaaa;
        n1.relativePosition = 0xbbbbbbbb;
        n1.height = 0x33445566;
        n1.leftIsPrevious = true;
        n1.rightIsNext = true;
        Node2 n2 = new Node2();
        Thread.sleep(1000*1000);
    }
}


// oop size == 8
class Node1{
    /** 左子树或者中序遍历的前节点.*/
    public int left; // seq = 1
    /** true表示left字段是左子树；false表示left字段是中序遍历的前节点 */
    public boolean leftIsPrevious;
    /** 右子树或者中序遍历的后节点 */
    public int right; // seq = 2
    /** true表示right字段是右子树；false表示right字段是中序遍历的后节点 */
    public boolean rightIsNext;
    /** How many levels of left/right are below this one. */
    public int height; // seq = 3
    /** 在List中的索引相对于父节点索引的偏移量；根节点就是根节点的索引*/
    public int relativePosition; // seq = 4
    /** 节点所保存的有效荷载 */
    public int value; // seq = 5
}

// oop size == 6
class Node2{
    Object element;
    Node2 next;
    Node2 previous;
}