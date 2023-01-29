package infoborden;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class QueueListener implements MessageListener {
	private String consumerName;
	private InfobordController infobordController;
	private Berichten berichten;
	
	public QueueListener(String consumerName, InfobordController infobordController, Berichten berichten) {
		this.consumerName = consumerName;
		this.infobordController=infobordController;
		this.berichten=berichten;
	}

	public void onMessage(Message message) {
		try {
			if (message instanceof TextMessage) {
	            TextMessage textMessage = (TextMessage) message;
				String text = textMessage.getText();
//				System.out.println("Consumer("+consumerName+")");
				berichten.nieuwBericht(text);
				infobordController.updateView();
			} else {
	            System.out.println("Consumer("+consumerName+"): Received: " + message);
	        }
		} 	        catch (JMSException e) {
			e.printStackTrace();
    	}
	}
}

