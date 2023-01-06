package com.main.sams.server.server;

import java.io.Serializable;


/**
 * SocketPackage is a class that is used to send data between the server and the client.
 * It contains a type of request, and can store two objects.
 *
 * @author cpprian
 * @version 1.0
 * @since 2023-01-06
 */
public class SocketPackage implements Serializable {
    /**
     * The type of request that is being sent.
     */
    private RequestType requestType;
    /**
     * The first object that is being sent.
     */
    private Object object1;
    /**
     * The second object that is being sent.
     */
    private Object object2;

    /**
     * Constructor for SocketPackage.
     *
     * @param requestType The type of request that is being sent.
     * @param object1 The first object that is being sent.
     * @param object2 The second object that is being sent.
     */
    public SocketPackage(RequestType requestType, Object object1, Object object2) {
        this.requestType = requestType;
        this.object1 = object1;
        this.object2 = object2;
    }

    /**
     * Getter for requestType.
     *
     * @return The type of request that is being sent.
     */
    public RequestType getRequestType() {
        return requestType;
    }

    /**
     * Getter for object1.
     *
     * @return The first object that is being sent.
     */
    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }

    /**
     * Getter for object1.
     *
     * @return The first object that is being sent.
     */
    public Object getObject1() {
        return object1;
    }

    /**
     * Setter for object1.
     *
     * @param object The first object that is being sent.
     */
    public void setObject1(Object object) {
        this.object1 = object;
    }

    /**
     * Getter for object2.
     *
     * @return The second object that is being sent.
     */
    public Object getObject2() {
        return object2;
    }

    /**
     * Setter for object2.
     *
     * @param object The second object that is being sent.
     */
    public void setObject2(Object object) {
        this.object2 = object;
    }
}
