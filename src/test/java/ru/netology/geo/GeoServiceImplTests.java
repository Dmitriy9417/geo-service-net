package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GeoServiceImplTests {

    public static Stream<Arguments> byipTest() {
        return Stream.of(Arguments.of("127.0.0.1", null),
                Arguments.of("172.0.32.11", Country.RUSSIA),
                Arguments.of("96.44.183.149", Country.USA),
                Arguments.of("172.14.12", Country.RUSSIA),
                Arguments.of("96.124.214", Country.USA));
    }

    @ParameterizedTest
    @MethodSource
    public void byipTest(String ip, Country answer) {

        GeoService geoService = new GeoServiceImpl();
        Location result = geoService.byIp(ip);

        assertEquals(answer, result.getCountry());
    }

    @Test
    public void byCoordinatesTest() {
        GeoService geoService = new GeoServiceImpl();

        assertThrows(
                RuntimeException.class, // Ожидаемый тип исключения
                () -> geoService.byCoordinates(2.3,2.2) // Лямбда-выражение, вызывающее метод
        );
    }
}
