package com.worksplace.MiniPro.Core.generic.L7.P2;

import java.util.HashMap;

public class HttpRequestBuilderImpl implements BuilderStage, MethodStage, UrlStage, HeaderStage, BodyStage{
    String method;
    String url;
    HashMap<String, String> header;
    String body;
    Integer timeout;

    public HttpRequestBuilderImpl(){
        this.header = new HashMap<>();
    }

    @Override
    public MethodStage method(String s) {
        this.method = s;
        return this;
    }

    @Override
    public UrlStage url(String s) {
        url = s;
        return this;
    }

    @Override
    public HeaderStage header(String s1, String s2) {
        header.put(s1,s2);
        return this;
    }

    @Override
    public BodyStage body(String s) {
        body = s;
        return this;
    }

    @Override
    public BodyStage timeout(Integer i) {
        timeout = i;
        return this;
    }

    @Override
    public HttpRequest build() {
        return new HttpRequest(method,url,header,body,timeout);
    }
}
