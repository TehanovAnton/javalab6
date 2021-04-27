package program;

public class Letter {
    public String sender;
    public String recipient;
    public String topic;
    public String content;
    public String sendingDate;

    public Letter(String sender, String recipient, String topic, String content, String sendingDate) {
        this.sender = sender;
        this.recipient = recipient;
        this.topic = topic;
        this.content = content;
        this.sendingDate = sendingDate;
    }
}
