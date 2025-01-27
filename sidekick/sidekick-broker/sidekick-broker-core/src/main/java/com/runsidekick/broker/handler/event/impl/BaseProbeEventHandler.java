package com.runsidekick.broker.handler.event.impl;

import com.runsidekick.broker.model.BaseProbe;
import com.runsidekick.broker.model.NotificationType;
import com.runsidekick.broker.model.event.Event;
import com.runsidekick.broker.proxy.Communicator;
import com.runsidekick.broker.proxy.listener.ProbeEventListener;
import com.runsidekick.broker.service.WebhookMessageService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author yasin.kalafat
 */
public abstract class BaseProbeEventHandler<P extends BaseProbe, E extends Event>
        extends BaseEventHandler<E> {

    protected final Logger logger = LogManager.getLogger(getClass());

    protected NotificationType notificationType;

    @Autowired
    protected Communicator communicator;

    @Autowired
    protected WebhookMessageService webhookMessageService;

    @Autowired
    protected ProbeEventListener probeEventListener;

    protected BaseProbeEventHandler(String eventName, Class<E> eventClass) {
        super(eventName, eventClass);
    }

    protected BaseProbeEventHandler(String eventName, Class<E> eventClass, NotificationType notificationType) {
        this(eventName, eventClass);
        this.notificationType = notificationType;
    }

    protected abstract void sendWebhookMessage(String messageRaw, P probe);

}
