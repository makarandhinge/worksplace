package com.worksplace.MiniPro.Core.generic;

import java.util.ArrayList;
import java.util.List;

// Acts like a database table
class Repositorys {
    private List<Object> data = new ArrayList<>();

    public void save(Object obj) {
        data.add(obj);
    }

    public Object findByIndex(int index) {
        return data.get(index);
    }

    public List<Object> findAll() {
        return data;
    }
}

// Business layer

class UserService {
    private Repositorys repository = new Repositorys();

    public void addUser(User user) {
        repository.save(user);
    }

    public User getUser(int index) {
        return (User) repository.findByIndex(index); // cast
    }
}

class ProductService {
    private Repositorys repository = new Repositorys();

    public void addProduct(Product product) {
        repository.save(product);
    }

    public Product getProduct(int index) {
        return (Product) repository.findByIndex(index); // cast
    }
}

// Models
class User {
    String name;
    User(String name) { this.name = name; }
}

class Product {
    String name;
    Product(String name) { this.name = name; }
}

public class MainApp {
    public static void main(String[] args) {
        UserService userService = new UserService();
        userService.addUser(new User("Alice"));
        User u = userService.getUser(0);

        ProductService productService = new ProductService();
        productService.addProduct(new Product("Laptop"));
        Product p = productService.getProduct(0);
    }
}
