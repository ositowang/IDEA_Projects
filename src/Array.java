public class Array {
    private int[] data;
    private int size;

    /**
     *
     * @param capacity
     */
    public Array(int capacity){
        data = new int[capacity];
        size=0;
    }

    /**
     * create a static array
     */
    public Array(){
        this(10);
    }
    /**
     *  Return the array size
     */
    public int GetSize(){
        return size;
    }

    /**
     *
     * return the array capacity
     */
    public int getCapacity(){
        return data.length;
    }

    /**
     * return whether the array is empty
     */
    public boolean isEmpty(){
        return size == 0 ;
    }

    /**
     *  add data into the array
     */
    public void addlast(int e){
        add(size,e);
    }
    /**
     * add to the first place of the arrary
     */
    public void addfirst(int e){
        add(0,e);
    }
    /**
     *  add data into specific index
     */
    public void add(int index, int e){
        if(size==data.length){
            throw new IllegalArgumentException("The array is full");
        }
        if(index<0 || index>size){
            throw new IllegalArgumentException("index should be >=0 and less than the capacity of the array");
        }
        for(int i = size-1; i>=index;i--){
            data[i+1]=data[i];
        }
        data[index] = e;
        size++;
    }
    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        result.append(String.format("Array Size = %d, capacity = %d\n", size, data.length));
        result.append("[");
        for(int i =0;i<size;i++) {
            result.append(data[i]);
            if (i != size - 1) {
                result.append(",");
            }
        }
        result.append("]");
        return result.toString();
    }

    /**
     *  get data from array
     */
    int get(int index){
        if(index<0 || index > size){
            throw new IllegalArgumentException("Get Failed, illegal index");
        }
        return data[index];
    }
    /**
     *  set the index position value
     */
    void set(int index, int e){
        if(index<0 || index > size){
            throw new IllegalArgumentException("Get Failed, illegal index");
        }
        data[index]=e;
    }
}
