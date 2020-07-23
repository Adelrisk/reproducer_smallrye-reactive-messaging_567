package org.acme;

import java.util.concurrent.CompletionStage;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(MessageConsumer.class);

    @Incoming("my_channel")
    CompletionStage<Void> onReceived(Message<String> message) {

	var payload = message.getPayload();
	LOG.info("Received message: {}", payload);

	return message.ack();
    }

}
