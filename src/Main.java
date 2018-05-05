public class Main {
    public static  void main(String[] args){
        Array<Integer> arr = new Array<>();
        for(int i=0;i<10;i++){
            arr.addlast(i);
        }
        arr.add(1,14);
        System.out.println(arr);
    }
}
