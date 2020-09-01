package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.AirRoute;
import ru.netology.exception.NotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class AirRouteRepositoryTest {
    private AirRouteRepository repository = new AirRouteRepository();
    private AirRoute first = new AirRoute(1, 10_000, "SVO", "KHV", 480);
    private AirRoute second = new AirRoute(2, 20_000, "SVO", "CDG", 600);
    private AirRoute third = new AirRoute(3, 5000, "GOJ", "MRV", 150);

    @BeforeEach
    public void setUp() {
        repository.save(first);
        repository.save(second);
        repository.save(third);
    }

    @Test
    public void shouldThrowCheckedException() {
        int idToRemove = 4;
        assertThrows(NotFoundException.class, () -> repository.removeById(idToRemove));
    }

    @Test
    public void shouldNotThrowCheckedException() {
        int idToRemove = 1;
        repository.removeById(idToRemove);
        AirRoute[] expected = new AirRoute[]{second, third};
        AirRoute[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

}