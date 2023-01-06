package com.main.sams.server.server;

import java.io.Serializable;

public class SocketPackage implements Serializable {
    private RequestType requestType;
    private Object object1;
    private Object object2;

    public SocketPackage(RequestType requestType, Object object1, Object object2) {
        this.requestType = requestType;
        this.object1 = object1;
        this.object2 = object2;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }

    public Object getObject1() {
        return object1;
    }

    public void setObject1(Object object) {
        this.object1 = object;
    }

    public Object getObject2() {
        return object2;
    }

    public void setObject2(Object object) {
        this.object2 = object;
    }
}
