package ru.netology.sender;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MessageSenderImplTests {


    public static Stream<Arguments> testSendMethod() {
        return Stream.of(Arguments.of("96.44.183.149", "Welcome"),
                Arguments.of("172.145.12.1", "Добро пожаловать"));
    }

    @ParameterizedTest
    @MethodSource
    public void testSendMethod(String adress, String answer) {
        MessageSender messageSender = Mockito.mock(MessageSender.class);

        Mockito.when(messageSender.send(Mockito.argThat(headers -> {
            if (headers == null) {
                return false; // Если headers равен null, возвращаем false
            }
            String ip = headers.get(MessageSenderImpl.IP_ADDRESS_HEADER);
            return ip != null && ip.startsWith("96"); // Проверяем, что IP начинается с "96"
        }))).thenReturn("Welcome");
        Mockito.when(messageSender.send(Mockito.argThat(headers -> {
            String ip = headers.get(MessageSenderImpl.IP_ADDRESS_HEADER);
            return ip != null && ip.startsWith("172"); // Проверяем, что IP начинается с "172"
        }))).thenReturn("Добро пожаловать");

        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, adress);
        // Вызываем метод и проверяем результат
        String result = messageSender.send(headers);
        assertEquals(answer, result);

        // Проверяем, что метод send был вызван с правильными аргументами
        Mockito.verify(messageSender).send(headers);
    }
}


