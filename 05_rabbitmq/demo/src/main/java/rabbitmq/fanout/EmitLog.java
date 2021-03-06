package rabbitmq.fanout;

import java.util.Map;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class EmitLog {

	private static final String EXCHANGE_NAME = "Fanout";
    private static final boolean DURABLE_EXCHANGE = true;
    private static final boolean DURABLE_QUEUE = false;
    private static final boolean EXCLUSIVE = true;
    private static final boolean AUTO_DELETE = true;
    private static final Map<String, Object> ARGUMENTS = null;
    
    
	  public static void main(String[] argv) throws Exception {
	    ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.2.46");
        factory.setUsername("admin");
        factory.setPassword("123456123");
	    try (Connection connection = factory.newConnection();
	         Channel channel = connection.createChannel()) {
	        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

	        String message = argv.length < 1 ? "info: Hello World!" :
	                            String.join(" ", argv);

	        channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes("UTF-8"));
	        System.out.println(" [x] Sent '" + message + "'");
	    }
	  }
	}