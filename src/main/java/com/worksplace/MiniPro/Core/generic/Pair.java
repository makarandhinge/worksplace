package com.worksplace.MiniPro.Core.generic;

public class Pair<A, B>{
    private A First;
    private B Second;

    public A getFirst(){
        return First;
    }

    public B getSecond(){
        return Second;
    }

    public void setFirst(A value){
        First = value;
    }

    public void setSecond(B value){
        Second = value;
    }
}
