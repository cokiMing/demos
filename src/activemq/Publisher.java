package activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by wuyiming on 2017/9/21.
 */
public class Publisher {

    private final static String BROKER_URL = "10.0.0.95";

    private Session session;

    private MessageProducer producer;

    private Destination destination;

    public Publisher() throws JMSException {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(BROKER_URL);
        Connection connection = factory.createConnection();
        try{
            connection.start();
        } catch (JMSException jmse) {
            connection.close();
            throw jmse;
        }

        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        producer = session.createProducer(null);
    }

    private void setTopic(String topic) throws JMSException {
        destination = session.createTopic(topic);
    }

    public void sendMessage(String topic) throws JMSException {
        Message message = createMessage(topic,session);
        producer.send(destination,message);
    }

    private Message createMessage(String topic, Session session) throws JMSException {
        MapMessage message = session.createMapMessage();
        message.setString("topic",topic);
        return message;
    }
}
