package ru.one.stream.server.gateway;

import ru.one.stream.commons.Message;

public interface ServerTransportService {

    Message recieveAndSend(Message message);
}
