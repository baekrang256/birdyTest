package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.requests.restaction.MessageCreateAction;
import org.example.commands.ExclamListener;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class ExclamListenerTest {
    @Mock
    MessageReceivedEvent event;
    @Mock
    Message msg;
    @Mock
    MessageChannelUnion msgChannelUnion;
    @Mock
    MessageCreateAction msgCreateAction;
    @Mock
    User user;
    String content;
    ExclamListener listener;

    /*
     * Message event has quite complicated structure.
     * We make the mocking of event, channel and message for each test.
     * content of the message will vary for each test, which is set individually for each test.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(event.getAuthor()).thenReturn(user);
        when(event.getMessage()).thenReturn(msg);
        when(event.getChannel()).thenReturn(msgChannelUnion);
        when(msgChannelUnion.sendMessage(anyString())).thenReturn(msgCreateAction);
        listener = new ExclamListener();
    }

    @Test
    void testBotRefusal() {
        when(user.isBot()).thenReturn(Boolean.TRUE);

        listener.onMessageReceived(event);
        verify(event, times(0)).getMessage();
    }

    @Test
    void testPingMsg() {
        content = "!ping";
        ArgumentCaptor<String> messageCaptor = ArgumentCaptor.forClass(String.class);

        when(user.isBot()).thenReturn(Boolean.FALSE);
        when(msg.getContentRaw()).thenReturn(content);

        listener.onMessageReceived(event);
        verify(msgChannelUnion).sendMessage(messageCaptor.capture());

        assertEquals("Pong!", messageCaptor.getValue());
    }
}
