
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.PriorityBlockingQueue;

/**
* A class which handles sending and reading messages using a Socket connection.
* This class is used by both the client and server programs.
*/
public class R2D2Connection implements Runnable {
    private final Socket socket;
    private final ObjectOutputStream output;
    private final ObjectInputStream input;

    // A queue of incoming messages.
    private final PriorityBlockingQueue<Message> messageQueue;

    public R2D2Connection(Socket c) throws IOException {
        socket = c;
        output = new ObjectOutputStream(socket.getOutputStream());
        input = new ObjectInputStream(socket.getInputStream());

        // Create a comparator for comparing message priorities.
        Comparator messageComparator = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                Message m1 = (Message) o1;
                Message m2 = (Message) o2;
                if(m1.getPriority() < m2.getPriority()) {
                    return -1;
                } else if(m1.getPriority() == m2.getPriority()) {
                    return 0;
                } else {
                    return 1;
                }
            }
        };

        // Set up the priority queue for incoming messages.
        messageQueue = new PriorityBlockingQueue(5, messageComparator);
    }

    /**
     * Send a message over the connection.
     * @param message The Message object to send.
     */
    public void sendMessage(Message message){
        try {
            output.writeObject(message);
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    /**
     * @return true if a message is in the queue to be read.
     */
    public boolean hasMessage() {
        return !messageQueue.isEmpty();
    }

    /**
     * Reads the next available message. Returns null if no messages are available.
     * @return The Message object which was read off the connection.
     */
    public Message readMessage() {
        return messageQueue.poll();
    }

    @Override
    public void run() {
        while(true) {
             try {
                 messageQueue.add((Message) input.readObject());
             } catch (IOException | ClassNotFoundException e) {
                 System.out.println(e.getLocalizedMessage());
             }
        }
    }
}