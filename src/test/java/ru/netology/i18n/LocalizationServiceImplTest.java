package ru.netology.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.sender.MessageSenderImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

class LocalizationServiceImplTest {

    private LocalizationService localizationService;

    @BeforeEach
    void setLocalizationService() {
        localizationService = Mockito.spy(LocalizationServiceImpl.class);
    }

    @Test
    public void whenRussian_thenMessageRussian() {
        String expectedRussianMessage = "Добро пожаловать";
        String actualMessage = localizationService.locale(Country.RUSSIA);
        assertEquals(expectedRussianMessage, actualMessage, "The message should be in Russian for Russia");

        verify(localizationService, Mockito.times(1)).locale(Country.RUSSIA);

        Country expectedValue = Country.valueOf("RUSSIA");
        ArgumentCaptor<Country> argumentCaptor = ArgumentCaptor.forClass(Country.class);
        verify(localizationService).locale(argumentCaptor.capture());
        Assertions.assertEquals(expectedValue, argumentCaptor.getValue());

    }

    @Test
    public void whenUSA_thenMessageEnglish() {
        String expectedEnglishMessage = "Welcome";
        String actualMessage = localizationService.locale(Country.USA);
        assertEquals(expectedEnglishMessage, actualMessage, "The message should be in English for USA");

        verify(localizationService, Mockito.times(1)).locale(Country.USA);

        Country expectedValue = Country.valueOf("USA");
        ArgumentCaptor<Country> argumentCaptor = ArgumentCaptor.forClass(Country.class);
        verify(localizationService).locale(argumentCaptor.capture());
        Assertions.assertEquals(expectedValue, argumentCaptor.getValue());
    }
}