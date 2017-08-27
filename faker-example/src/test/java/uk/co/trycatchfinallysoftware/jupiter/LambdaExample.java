package uk.co.trycatchfinallysoftware.jupiter;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @see {https://github.com/mvpjava/JUnit5-tutorial}
 */
@RunWith(JUnitPlatform.class)
class LambdaExample {
    private org.slf4j.Logger Logger = LoggerFactory.getLogger(getClass());

    public org.slf4j.Logger getLogger() {
        return Logger;
    }

    @BeforeEach
    void beforeEach() {
        getLogger().info("Jupiter BeforeEach called ");
    }

    /*
     * Using lambdas
     */
    @Test
    @DisplayName("Testing out lambda expressions")
    void lambdaExpressions() {
        assertTrue(Stream.of(1, 2, 3)
                .mapToInt(i -> i)
                .sum() > 5, () -> "Sum should be greater than 5");
    }
}
