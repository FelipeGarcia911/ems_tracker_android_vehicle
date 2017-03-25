package co.original.codigo.ems_tracker.helpers.socket_io;

import java.net.URISyntaxException;

import co.original.codigo.ems_tracker.eventBusEvents.SocketEvent;
import co.original.codigo.ems_tracker.helpers.Constants;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

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
            socket = IO.socket(Constants.URL_SOCKET_CONN);
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

    public boolean emitSocketMessage(String socketMethod, Object data){
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

    private void bindSocketLocalEvents(){
        if (socket != null){

            socket.on(Socket.EVENT_CONNECTING, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    updateSocketEventBus(Socket.EVENT_CONNECTING);
                }
            });

            socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    updateSocketEventBus(Socket.EVENT_CONNECT);
                }
            });

            socket.on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    updateSocketEventBus(Socket.EVENT_DISCONNECT);
                }
            });
        }
    }

    private void updateSocketEventBus(String eventType){
        SocketEvent socketEvent = new SocketEvent();
        socketEvent.setEventType(eventType);
    }

}
