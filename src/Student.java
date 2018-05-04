public class Student {

    private String name;
    private int score;
    public Student(String studentName, int studentScore){
        name = studentName;
        score = studentScore;
    }
    @Override
    public String toString(){
        return String.format("Student(name: %s, score: %d)", name,score);
    }

    public static void  main(String[] args){
        Array<Student> arr = new Array<>();
        arr.addlast(new Student("Alice",100));
        arr.addlast(new Student("Bob",50));
        arr.addlast(new Student("Charlie",70));
        System.out.println(arr);
    }
}
