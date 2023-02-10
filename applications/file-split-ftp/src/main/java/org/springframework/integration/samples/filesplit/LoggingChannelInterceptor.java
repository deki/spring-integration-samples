package org.springframework.integration.samples.filesplit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.integration.config.GlobalChannelInterceptor;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.support.ExecutorChannelInterceptor;
import org.springframework.stereotype.Component;

@Component
@GlobalChannelInterceptor
public class LoggingChannelInterceptor implements ExecutorChannelInterceptor {

    private final Log logger = LogFactory.getLog(LoggingChannelInterceptor.class);

    @Override
    public Message<?> beforeHandle(Message<?> message, MessageChannel channel, MessageHandler handler) {
        logger.info("beforeHandle " + message.getHeaders());
        return message;
    }

    @Override
    public void afterMessageHandled(Message<?> message, MessageChannel channel, MessageHandler handler, Exception ex) {
        logger.info("afterMessageHandled " + message.getHeaders());
    }

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        logger.info("preSend " + message.getHeaders());
        return message;
    }

    @Override
    public void postSend(Message<?> message, MessageChannel channel, boolean sent) {
        logger.info("postSend " + message.getHeaders());
    }

    @Override
    public void afterSendCompletion(Message<?> message, MessageChannel channel, boolean sent, Exception ex) {
        logger.info("beforeHandle " + message.getHeaders());
    }

    @Override
    public boolean preReceive(MessageChannel channel) {
        logger.info("preReceive " + channel);
        return ExecutorChannelInterceptor.super.preReceive(channel);
    }

    @Override
    public Message<?> postReceive(Message<?> message, MessageChannel channel) {
        logger.info("postReceive " + message.getHeaders());
        return message;
    }

    @Override
    public void afterReceiveCompletion(Message<?> message, MessageChannel channel, Exception ex) {
        if (message!=null) {
            logger.info("afterReceiveCompletion " + message.getHeaders());
        }
    }
}
