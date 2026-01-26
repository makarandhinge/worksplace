package com.worksplace.MiniPro.Core.generic.L7.P1;

public class Main {
    public static void main(String[] args) {
    Query<User> q = Query
            .select("name","age")
            .from(User.class)
            .where(user -> user.getAge() > 18)
            .and(u -> u.getName().startsWith("A"))
            .limit(10)
            .build();

        System.out.println(q);
    }

}
