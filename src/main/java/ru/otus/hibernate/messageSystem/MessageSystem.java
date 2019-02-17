package ru.otus.hibernate.messageSystem;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author tully
 */
@SuppressWarnings("LoopStatementThatDoesntLoop")
public final class MessageSystem {
    private final static Logger logger = Logger.getLogger(MessageSystem.class.getName());
    private static final int DEFAULT_STEP_TIME = 10;

    private final List<Thread> workers;
    private final Map<Address, LinkedBlockingQueue<Message>> messagesMap;
    private final Map<Address, Addressee> addresseeMap;

    public MessageSystem() {
        workers = new ArrayList<>();
        messagesMap = new HashMap<>();
        addresseeMap = new HashMap<>();
    }

    public void addAddressee(Addressee addressee) {
        addresseeMap.put(addressee.getAddress(), addressee);
        messagesMap.put(addressee.getAddress(), new LinkedBlockingQueue<>());
        start(addressee.getAddress(), addressee);
    }

    public void sendMessage(Message message) {
        messagesMap.get(message.getTo()).add(message);
    }

    private void start(Address address, Addressee addressee) {
        String name = "MS-worker-" + address.getId();
        Thread thread = new Thread(() -> {
            while (true) {
                LinkedBlockingQueue<Message> queue = messagesMap.get(address);
                while (true) {
                    Message message;
                    try {
                        message = queue.take();
                    } catch (InterruptedException e) {
                        logger.log(Level.INFO, "Thread interrupted. Finishing: " + address.getId());
                        return;
                    }
                    try {
                        message.exec(addressee);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.setName(name);
        thread.start();
        workers.add(thread);
    }

    public void dispose() {
        workers.forEach(Thread::interrupt);
    }
}