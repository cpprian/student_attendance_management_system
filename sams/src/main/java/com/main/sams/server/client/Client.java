package com.main.sams.server.client;

import com.main.sams.server.server.RequestType;
import com.main.sams.server.server.SocketPackage;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    private static Client instance = null;
    private static final int PORT = 8080;

    private Socket clientSocket = null;
    private ObjectOutputStream out = null;
    private ObjectInputStream in = null;
    private System.Logger logger = null;

    private Client(String hostname) {
        try {
            logger = System.getLogger("Client");
            clientSocket = new Socket(hostname, PORT);
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            in = new ObjectInputStream(clientSocket.getInputStream());
        } catch (Exception e) {
            logger.log(System.Logger.Level.ERROR, "Client constructor: Client failed to connect to server");
            System.exit(1);
        }
    }

    public static Client getInstance(String hostname) {
        if (instance == null) {
            instance = new Client(hostname);
        }
        return instance;
    }

    public void cleanUpClient() {
        try {
            if (clientSocket != null) {
                clientSocket.close();
            }
            if (out != null) {
                out.close();
            }
            if (in != null) {
                in.close();
            }
        } catch (Exception e) {
            logger.log(System.Logger.Level.ERROR, "Client cleanUpClient: Failed to close client");
        }
    }

    public void sendRequest(SocketPackage socketPackage) {
        try {
            out.writeObject(socketPackage);
            logger.log(System.Logger.Level.INFO, "Sent request to server");
        } catch (Exception e) {
            logger.log(System.Logger.Level.ERROR, "Client sendRequest: Failed to send request to server");
        }
    }

    public SocketPackage receiveResponse() {
        try {
            SocketPackage socketPackage = (SocketPackage) in.readObject();
            if (socketPackage.getRequestType() == RequestType.SERVER_REJECTED) {
                logger.log(System.Logger.Level.ERROR, "Client receiveResponse: Server rejected request");
                return null;
            }
            return socketPackage;
        } catch (Exception e) {
            logger.log(System.Logger.Level.ERROR, "Client receiveResponse: Failed to receive response from server");
            return null;
        }
    }
}
