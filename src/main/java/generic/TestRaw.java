package generic;

import java.util.ArrayList;
import java.util.List;

public class TestRaw {
    public static void main(String[] args) {

        List list = new ArrayList();
        list.add("Hello");
        list.add(10);

        String s =  (String) list.get(1);
        System.out.println(s);


    }


}
