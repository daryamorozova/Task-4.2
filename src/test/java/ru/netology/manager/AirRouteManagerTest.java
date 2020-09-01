package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.AirRoute;
import ru.netology.domain.AirRouteByTimeAscComparator;
import ru.netology.repository.AirRouteRepository;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class AirRouteManagerTest {
    private AirRouteRepository repository = new AirRouteRepository();
    private AirRouteManager manager = new AirRouteManager(repository);
    private AirRoute first = new AirRoute(1, 10_000, "SVO", "KHV", 480);
    private AirRoute second = new AirRoute(2, 20_000, "SVO", "CDG", 600);
    private AirRoute third = new AirRoute(3, 5000, "GOJ", "MRV", 150);
    private AirRoute forth = new AirRoute(4, 22_000, "SVO", "CDG", 700);
    private AirRoute fifth = new AirRoute(5, 25_000, "SVO", "CDG", 800);

    @BeforeEach
    public void setUp() {
        manager.add(first);
        manager.add(second);
        manager.add(third);
        manager.add(forth);
        manager.add(fifth);
    }

    @Test
    public void shouldNotFound() {
        String airportFrom = "VKO";
        String airportTo = "PAR";

        AirRoute[] expected = new AirRoute[]{};
        AirRoute[] actual = manager.findAllPriceSort(airportFrom, airportTo);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotFoundOne() {
        String airportFrom = "SVO";
        String airportTo = "PAR";

        AirRoute[] expected = new AirRoute[]{};
        AirRoute[] actual = manager.findAllPriceSort(airportFrom, airportTo);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotFoundTwo() {
        String airportFrom = "PAR";
        String airportTo = "CDG";

        AirRoute[] expected = new AirRoute[]{};
        AirRoute[] actual = manager.findAllPriceSort(airportFrom, airportTo);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFoundAllPriceSort() {
        String airportFrom = "SVO";
        String airportTo = "CDG";

        AirRoute[] expected = new AirRoute[]{second, forth, fifth};
        AirRoute[] actual = manager.findAllPriceSort(airportFrom, airportTo);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFoundAllTimeSort() {
        String airportFrom = "SVO";
        String airportTo = "CDG";
        Comparator<AirRoute> comparator = new AirRouteByTimeAscComparator();

        AirRoute[] expected = new AirRoute[]{second, forth, fifth};
        AirRoute[] actual = manager.findAllTimeSort(airportFrom, airportTo, comparator);
        assertArrayEquals(expected, actual);
    }

}