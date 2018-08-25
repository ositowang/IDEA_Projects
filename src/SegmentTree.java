public class SegmentTree<E> {
    private E[] tree;
    private E[] data;
    private Merger<E> merger;

    //构造函数，传入数组和融合器
    public SegmentTree(E[] arr, Merger<E> merger) {
        this.merger = merger;
        data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++)
            data[i] = arr[i];
        tree = (E[]) new Object[4 * arr.length];
        buildSegmentTree(0, 0, data.length - 1);
    }

    //在treeIndex位置创建了表示区间[l...r]的线段树
    private void buildSegmentTree(int treeIndex, int l, int r) {
        if (l == r) {
            tree[treeIndex] = data[l];
            return;
        }
        //避免整数溢出
        int mid = l + (r - l) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        buildSegmentTree(leftTreeIndex, l, mid);
        buildSegmentTree(rightTreeIndex, mid + 1, r);
        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    //获取大小
    public int getSize() {
        return data.length;
    }

    //按下标取值
    public E get(int index) {
        if (index < 0 || index > data.length)
            throw new IllegalArgumentException("Illegal Index");
        return data[index];
    }

    //辅助函数，查找当前下标的左子树的下标
    private int leftChild(int index) {
        return 2 * index + 1;
    }

    //辅助函数，查找当前下标的右子树的下标
    private int rightChild(int index) {
        return 2 * index + 2;
    }

    //线段树的查询[l...r]区间的值
    public E query(int queryL, int queryR) {
        if (queryL < 0 || queryL >= data.length ||
                queryR < 0 || queryR >= data.length || queryL > queryR)
            throw new IllegalArgumentException("Index is illegal.");
        return query(0, 0, data.length - 1, queryL, queryR);
    }


    // 在以treeIndex为根的线段树中[l...r]的范围里，搜索区间[queryL...queryR]的值
    private E query(int treeIndex, int l, int r, int queryL, int queryR) {
        if (l == queryL && r == queryR) {
            return tree[treeIndex];
        }
        //切分一下区间
        int mid = l + (r - l) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        //如果待查找的区间的左边比mid+1还大，那就不用查找左子树了，直接去右子树就好
        if (queryL >= mid + 1)
            return query(rightTreeIndex, mid + 1, r, queryL, queryR);
            //如果带查找的区间右边比mid还小，那就不查找右子树了
        else if (queryR <= mid)
            return query(leftTreeIndex, l, mid, queryL, queryR);
        //如果各有一部分落在两个区间里
        //落在左区间的就在左区间找
        E leftResult = query(leftTreeIndex, l, mid, queryL, mid);
        //落在右区间的就在右区间找
        E rightResult = query(rightTreeIndex, mid + 1, r, mid + 1, queryR);
        //最后根据融合器的定义，融合一下两个结果
        return merger.merge(leftResult, rightResult);
    }

    //更新线段树index位置的值为e
    public void set(int index, E e) {
        if (index < 0 || index >= data.length)
            throw new IllegalArgumentException("Index is illegal");
        data[index] = e;
        set(0, 0, data.length - 1, index, e);
    }

    //在以treeIndex为根的线段树中更新index的值为e
    private void set(int treeIndex, int l, int r, int index, E e) {
        if (l == r) {
            tree[treeIndex] = e;
            return;
        }
        //切分一下区间
        int mid = (r+l)/2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        //如果待查找的区间的左边比mid+1还大，那就不用查找左子树了，直接去右子树就好
        if(index>=mid+1)
            set(rightTreeIndex,mid+1,r,index,e);
            //如果带查找的区间右边比mid还小，那就不查找右子树了
        else
            set(leftTreeIndex,l,mid,index,e);
        tree[treeIndex] = merger.merge(tree[leftTreeIndex],tree[rightTreeIndex]);
    }


    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("[");
        for (int i = 0; i < tree.length; i++) {
            if (tree[i] != null) {
                res.append(tree[i]);
            } else {
                res.append("null");
            }
            if (i != tree.length - 1)
                res.append(", ");
        }
        res.append("]");
        return res.toString();
    }

    //测试函数
    public static void main(String[] args) {
        Integer[] nums = {-2, 0, 3, -5, 2, -1};
        SegmentTree<Integer> segTree = new SegmentTree<>(nums, (a, b) -> a + b);
        System.out.println(segTree);
        System.out.println(segTree.query(2, 5));
    }

}
