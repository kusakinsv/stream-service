package ru.one.stream.client.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.one.stream.commons.models.UserDetailsDto;
import ru.one.stream.client.gateway.ClientTransportService;
import ru.one.stream.commons.Message;
import ru.one.stream.commons.MessageHeader;
import ru.one.stream.commons.MessageType;
import ru.one.stream.commons.models.userspace.UserspaceDto;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    ClientTransportService clientTransportService;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public UserDetailsDto createUser(UserDetailsDto userTestDto) {
        return null;
    }

    public UserDetailsDto getUserDetailsByUsername(String username){
        Message requestMessage = new Message();
        requestMessage.setHeader(MessageHeader.MESSAGE_TYPE.name(), MessageType.USERS_GET_USER_DETAILS.name());
        requestMessage.setPayload(username);
        try {
            Message response = clientTransportService.sendMessage(requestMessage);
            return objectMapper.convertValue(response.getPayload(), UserDetailsDto.class);
        } catch (Exception e){
            throw new RuntimeException(String.format("User with username '%s' not found", username));
        }
    }

    public UserspaceDto getUserspaceByUsername(String username){
        Message requestMessage = new Message();
        requestMessage.setHeader(MessageHeader.MESSAGE_TYPE.name(), MessageType.USERS_GET_USERSPACE.name());
        requestMessage.setPayload(username);
//        try {
            Message response = clientTransportService.sendMessage(requestMessage);
            return objectMapper.convertValue(response.getPayload(), UserspaceDto.class);
//        } catch (Exception e){
//            throw new RuntimeException(String.format("User with username '%s' not found", username));
//        }
    }

}
