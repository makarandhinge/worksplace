package com.worksplace.MiniPro.Core.generic;

import java.util.function.Supplier;

public class Factory<T> {

    private Supplier<T> supplier;

    public Factory(Supplier<T> supplier){
        this.supplier = supplier;
    }

    public T create() {
        return supplier.get();
    }

}
