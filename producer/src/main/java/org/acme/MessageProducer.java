package org.acme;

import io.quarkus.runtime.StartupEvent;
import java.util.concurrent.CompletableFuture;
import javax.enterprise.event.Observes;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageProducer {

    private static final Logger LOG = LoggerFactory.getLogger(MessageProducer.class);

    @Channel("my_channel")
    Emitter<String> messageEmitter;

    void onStart(@Observes StartupEvent ev) {
	sendMessage("my_onstart_message");
    }

    void sendMessage(String payload) {
	LOG.info("Sending message: {}", payload);

	messageEmitter.send(Message.of(payload, () -> {
	    LOG.info(" Confirmed message: {}", payload);

	    return CompletableFuture.completedFuture(null);
	}));
    }

}
