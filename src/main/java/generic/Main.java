package generic;


public class Main {

    public static void main(String[] args) {

//        Repository<Box<String>> db = new InMemoryRepository<>();
//        Box<String> b1 = new Box<>();
//        b1.setValue("Value 01");
//        db.save(b1);
//        Box<String> b2 = new Box<>();
//        b2.setValue("Value 02");
//        db.save(b2);
//        System.out.println(db.findById(0));

//        Storage<String> names = new Storage<>();
//        names.add("Alice");
//        names.add("Bob");
//
//        String s = names.get(0);
//        System.out.println(s);

        Pair<String, Integer> p1 = new Pair<>();
        p1.setFirst("Age");
        p1.setSecond(25);

        String key = p1.getFirst();
        Integer value = p1.getSecond();
        System.out.println(key + " " + value);

//        Factory<Box<String>> f = new Factory<>(Box::new);
//        Box<String> b = f.create();
//        System.out.println(b);

//        Box<Integer> box = new Box<>();
//        box.setValue(20);
//
//        Box<String> box2 = new Box<>();
//        box2.setValue("Hello");
//
//        System.out.println(box.getValue());
//        System.out.println(box2.getValue());
//        box2.setValue(10);

//        Integer[] nums = {1,2,3};
//        String[] names = {"A","B"};
//
//        Util.printArray(nums);
//        Util.printArray(names);

//        System.out.println(Util.max(10,20));
//        System.out.println(Util.max("apple","banana"));

//        System.out.println(Util.max(new Car(),new Car()));

//        List<String> list1 = new ArrayList<>();
//        List<Integer> list2 = new ArrayList<>();
//
//        System.out.println(list1.getClass());
//        System.out.println(list2.getClass());

//        List raw = new ArrayList();
//        raw.add("Hello");
//
//        List<String> typed = raw;   // unchecked warning
//        String s = typed.get(0);


        }

}
