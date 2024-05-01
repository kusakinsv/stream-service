package ru.one.stream.client.gateway;

import ru.one.stream.commons.Message;

public interface ClientTransportService {

    Message sendMessage(Message request);

}
