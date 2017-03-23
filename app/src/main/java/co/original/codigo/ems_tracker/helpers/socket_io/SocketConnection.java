package co.original.codigo.ems_tracker.helpers.socket_io;

import java.net.URISyntaxException;

import co.original.codigo.ems_tracker.helpers.Contansts;
import io.socket.client.IO;
import io.socket.client.Socket;

public class SocketConnection {

    private Socket socket;

    private static class SingletonHolder {
        private static final SocketConnection INSTANCE = new SocketConnection();
    }

    public static SocketConnection getInstance() {
        return SocketConnection.SingletonHolder.INSTANCE;
    }

    public boolean initSocketConn() {
        try {
            socket = IO.socket(Contansts.URL_SOCKET_CONN);
            socket.connect();
            return true;
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public boolean emitSocketMessage(String socketMethod, String data){
        if (isSocketConnected()) {
            socket.emit(socketMethod, data);
            return true;
        }else{
            return false;
        }
    }

    public boolean isSocketConnected() {
        return socket != null && socket.connected();
    }

}
