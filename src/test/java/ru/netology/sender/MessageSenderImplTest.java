package ru.netology.sender;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class MessageSenderImplTest {

    @Mock
    private GeoService geoService;
    @Mock
    private LocalizationService localizationService;
    @InjectMocks
    private MessageSenderImpl messageSender;

    @BeforeEach
    void setUp() {
        messageSender = new MessageSenderImpl(geoService, localizationService);
    }

    @Test
    public void whenRussianIp_thenSendRussianMessage() {
        String messageRussian = "Добро пожаловать";

        Mockito.when(geoService.byIp(GeoServiceImpl.MOSCOW_IP))
                //return new Location("Moscow", Country.RUSSIA, "Lenina", 15);
                .thenReturn(new Location("Moscow", Country.RUSSIA, "Lenina", 15));

        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn(messageRussian);

        var actualMessage = messageSender.send(Map.of(MessageSenderImpl.IP_ADDRESS_HEADER, GeoServiceImpl.MOSCOW_IP));
        assertEquals(messageRussian, actualMessage);
    }

    @Test
    public void whenAmericanIp_thenSendEnglishMessage() {
        String messageEnglish = "Welcome";

        Mockito.when(geoService.byIp(GeoServiceImpl.NEW_YORK_IP))
                // return new Location("New York", Country.USA, " 10th Avenue", 32);
                .thenReturn(new Location("New York", Country.USA, " 10th Avenue", 32));

        Mockito.when(localizationService.locale(Country.USA))
                .thenReturn(messageEnglish);

        var actualMessage = messageSender.send(Map.of(MessageSenderImpl.IP_ADDRESS_HEADER, GeoServiceImpl.NEW_YORK_IP));
        assertEquals(messageEnglish, actualMessage);
    }


}


