package uk.co.trycatchfinallysoftware.jupiter;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Stream;

import static org.mockito.Mockito.*;

/**
 * @see {https://github.com/mvpjava/JUnit5-tutorial}
 */
@RunWith(JUnitPlatform.class)
class NestedExample {
    private org.slf4j.Logger Logger = LoggerFactory.getLogger(getClass());

    public org.slf4j.Logger getLogger() {
        return Logger;
    }

    @BeforeEach
    void beforeEach() {
        getLogger().info("Jupiter BeforeEach called ");
    }

    /*
    An arbitrary inner class for nested tests
     */
    @Nested
    class NestedTest {
        @BeforeEach
        @DisplayName("Nested Test before each")
        public void beforeNested() {
            // Do some typical mock/init()

            getLogger().info("Jupiter nested before each");
        }

        @DisplayName("A nested class method test")
        @Test
        public void firstNest() {
            getLogger().info("First Nested");
        }

        @Test
        public void secondNested() {
            getLogger().info("Second Nested");
        }
    }
}
