package amv.springwebsocketdemo.models;

public record Message(String room, String user, String content) {
}
