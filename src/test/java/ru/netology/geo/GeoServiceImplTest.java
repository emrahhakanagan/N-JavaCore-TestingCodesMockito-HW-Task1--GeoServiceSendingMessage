package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import static org.mockito.Mockito.verify;


class GeoServiceImplTest {
    private GeoService geoService;

    @BeforeEach
    void setGeoService() {
        geoService = Mockito.spy(GeoServiceImpl.class);
    }

    @Test
    public void whenRussianIp_thenLocationIsRussia() {
        String russianIp = "172.123.12.19";
        Location location = geoService.byIp(russianIp);

        Assertions.assertEquals(Country.RUSSIA, location.getCountry()
                , "Expected Russian location for IP starting with 172");

        verify(geoService, Mockito.times(1)).byIp(russianIp);

        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(geoService).byIp(argumentCaptor.capture());
        Assertions.assertEquals(russianIp, argumentCaptor.getValue());

    }

    @Test
    public void whenAmericanIp_thenLocationIsUSA() {
        String americanIp = "96.44.183.149";
        Location location = geoService.byIp(americanIp);

        Assertions.assertEquals(Country.USA, location.getCountry()
                , "Expected USA location for IP starting with 96");

        verify(geoService, Mockito.times(1)).byIp(americanIp);

        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(geoService).byIp(argumentCaptor.capture());
        Assertions.assertEquals(americanIp, argumentCaptor.getValue());

    }


}