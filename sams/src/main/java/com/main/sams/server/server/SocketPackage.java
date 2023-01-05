package com.main.sams.server.server;

public class SocketPackage {
    private RequestType requestType;
    private Object object;

    public SocketPackage(RequestType requestType, Object object) {
        this.requestType = requestType;
        this.object = object;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
