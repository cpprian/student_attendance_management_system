package com.main.sams.server.server;

import java.io.Serializable;
import java.util.ArrayList;

import com.main.sams.student.MyObject;

/**
 * SocketPackage is a class that is used to send data between the server and the client.
 * It contains a type of request, and can store two objects.
 *
 * @author cpprian
 * @version 1.0
 * @since 2023-01-06
 */
public class SocketPackage implements Serializable {
    private RequestType requestType;
    private int id1;
    private int id2;
    private ArrayList<MyObject> myObjects1;
    private ArrayList<MyObject> myObjects2;

    public SocketPackage(RequestType requestType, ArrayList<MyObject> myObjects1, ArrayList<MyObject> myObjects2, int id1, int id2) {
        this.requestType = requestType;
        this.myObjects1 = myObjects1;
        this.myObjects2 = myObjects2;
        this.id1 = id1;
        this.id2 = id2;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public ArrayList<MyObject> getMyObjects1() {
        return myObjects1;
    }

    public ArrayList<MyObject> getMyObjects2() {
        return myObjects2;
    }

    public int getId1() {
        return id1;
    }

    public int getId2() {
        return id2;
    }

    @Override
    public String toString() {
        return "SocketPackage [id=" + id1 + ", myObjects1=" + myObjects1 + ", myObjects2=" + myObjects2 + ", requestType="
                + requestType + "]";
    }
}
