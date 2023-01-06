package com.main.sams.server.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {

    // singleton
    private static Server instance = null;
    private ServerSocket serverSocket = null;
    private Socket clientSocket = null;
    private System.Logger logger = null;

    private static final int PORT = 8080;

    private ObjectOutputStream out = null;
    private ObjectInputStream in = null;

    private Server() {
        try {
            serverSocket = new ServerSocket(PORT);
            logger = System.getLogger("Server");
            logger.log(System.Logger.Level.INFO, "Server started on port " + PORT + " and ip address " + serverSocket.getInetAddress());
        } catch (Exception e) {
            logger.log(System.Logger.Level.ERROR, "Server constructor: Server failed to start on port " + PORT);
            System.exit(1);
        }
    }

    public static void main(String[] args) throws IOException {
        Server server = Server.getInstance();
        server.runServer();
        server.cleanUpServer();
    }

    public static Server getInstance() {
        if (instance == null) {
            instance = new Server();
        }
        return instance;
    }

    private void runServer() {
        try {
            // handle one client at a time
            clientSocket = serverSocket.accept();
            logger.log(System.Logger.Level.INFO, "Client connected: " + clientSocket.getInetAddress().getHostAddress());
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            in = new ObjectInputStream(clientSocket.getInputStream());

            receivePackage();
        } catch (Exception e) {
            logger.log(System.Logger.Level.ERROR, "Server runServer: Server failed to run");
            cleanUpServer();
            System.exit(1);
        }
    }

    private void cleanUpServer() {
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
            if (out != null) {
                out.close();
            }
            if (in != null) {
                in.close();
            }
        } catch (Exception e) {
            logger.log(System.Logger.Level.ERROR, "Server cleanUpServer: Server failed to clean up");
            System.exit(1);
        }
    }

    private void receivePackage() {
        try {
            while (clientSocket.isConnected()) {
                // read from client
                SocketPackage socketPackage = (SocketPackage) in.readObject();
                if (socketPackage == null) {
                    logger.log(System.Logger.Level.INFO, "Received package from client is null. Closing connection.");
                    continue;
                }
                logger.log(System.Logger.Level.INFO, "Received package from client: " + socketPackage);

                // process package
                new Thread(() -> {
                    sendPackage(processPackage(socketPackage));
                }).start();
            }
        } catch (Exception e) {
            if (e.getClass() == EOFException.class) {
                logger.log(System.Logger.Level.INFO, "Client disconnected");
            } else {
                logger.log(System.Logger.Level.ERROR, "Server receivePackage: Server failed to receive package, error message: " + e.getMessage() + " error type: " + e.getClass());
            }

            cleanUpServer();
            System.exit(1);
        }
    }

    private void sendPackage(SocketPackage socketPackage) {
        try {
            // write to client
            if (!clientSocket.isConnected()) {
                logger.log(System.Logger.Level.INFO, "Client is not connected. Closing connection.");
                return;
            }
            out.writeObject(socketPackage);
            out.flush();
            logger.log(System.Logger.Level.INFO, "Sent package to client: " + socketPackage);
        } catch (Exception e) {
            logger.log(System.Logger.Level.ERROR, "Server sendPackage: Server failed to send package");
            cleanUpServer();
            System.exit(1);
        }
    }

    private SocketPackage processPackage(SocketPackage socketPackage) {
        SocketPackage responsePackage = null;

        // TODO: process package
        switch (socketPackage.getRequestType()) {
            case ADD_STUDENT: {
                break;
            }
            case DELETE_STUDENT: {
                break;
            }
            case ADD_GROUP: {
                break;
            }
            case DELETE_GROUP: {
                break;
            }
            case ADD_STUDENT_TO_GROUP: {
                break;
            }
            case DELETE_STUDENT_FROM_GROUP: {
                break;
            }
            case ADD_CLASS_TIME: {
                break;
            }
            case ADD_ATTENDANCE: {
                break;
            }
            case PRINT_GROUP_ATTENDANCE: {
                break;
            }
            default: {
                logger.log(System.Logger.Level.ERROR, "Server processPackage: Invalid request type");
                responsePackage = new SocketPackage(RequestType.SERVER_REJECTED, null);
                break;
            }
        }
        return responsePackage;
    }
}
