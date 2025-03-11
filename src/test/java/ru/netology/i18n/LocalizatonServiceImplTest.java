package ru.netology.i18n;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.netology.entity.Country;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocalizatonServiceImplTest {


    @ParameterizedTest
    @EnumSource(Country.class)
    public void locale(Country country) {
        String expected = (country == Country.RUSSIA) ? "Добро пожаловать" : "Welcome";

        LocalizationService localizationService = new LocalizationServiceImpl();
        String actual = localizationService.locale(country);

        assertEquals(expected, actual);

    }
}
