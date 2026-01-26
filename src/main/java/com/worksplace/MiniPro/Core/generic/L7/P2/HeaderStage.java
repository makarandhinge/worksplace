package com.worksplace.MiniPro.Core.generic.L7.P2;

public interface HeaderStage {
    HeaderStage header(String s1, String s2);
    BodyStage body(String s);
    BodyStage timeout(Integer i);
    HttpRequest build();
}
