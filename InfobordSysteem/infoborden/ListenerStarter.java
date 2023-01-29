package infoborden;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public  class ListenerStarter implements Runnable, ExceptionListener {
	private String selector="JSONBerichten";
	private InfobordController infobordController;
	private Berichten berichten;
	
	public ListenerStarter() {
	}
	
	public ListenerStarter(String selector, InfobordController infobordController, Berichten berichten) {
		this.selector=selector;
		this.infobordController=infobordController;
		this.berichten=berichten;
	}

	public void run() {
        try {
            ActiveMQConnectionFactory connectionFactory = 
            		new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
            Connection connection = connectionFactory.createConnection();
            connection.start();
            connection.setExceptionListener(this);
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createTopic("INFOBord.TOPIC");
            MessageConsumer consumer = session.createConsumer(destination, selector);
            System.out.println("Produce, wait, consume"+ selector);
            consumer.setMessageListener(new QueueListener(selector, infobordController, berichten));
        } catch (Exception e) {
            System.out.println("Caught: " + e);
            e.printStackTrace();
        }
    }

    public synchronized void onException(JMSException ex) {
        System.out.println("JMS Exception occured.  Shutting down client.");
    }
}