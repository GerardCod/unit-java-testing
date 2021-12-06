package com.gerardo.testing.unit;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class StringTest {

	@BeforeAll
	static void beforeAll() {
		System.out.println("Initializing connection to the database");
	}

	@AfterAll
	static void afterAll() {
		System.out.println("Cleaning up the connection to the database");
	}

	@BeforeEach
	void setup(TestInfo info) {
		System.out.println("Initializing data before each test for: " + info.getDisplayName());
	}

	@AfterEach
	void dispose(TestInfo info) {
		System.out.println("Cleaning up the data for: " + info.getDisplayName());
	}

	@Test
	@RepeatedTest(2)
	void whatEverYouWant() {
		int actualLength = "ABCD".length();
		int expectedLength = 4;
		assertEquals(expectedLength, actualLength);
	}

	@Test
	@DisplayName("When name is null, throw an exception")
	void lengthException() {
		String str = null;
		assertThrows(NullPointerException.class, () -> {
			int actual = str.length();
			System.out.println(actual);
		});
	}

	@Test
	void toUpperCaseTest() {
		String name = "Gerardo";
		String expected = "GERARDO";
		assertEquals(expected, name.toUpperCase());
	}

	@Test
	void containsBasic() {
		assertFalse("abcde".contains("fg"));
	}

	@Test
	void splitBasic() {
		String str = "abc def ghi";
		String[] actual = str.split(" ");
		String[] expected = { "abc", "def", "ghi" };

		assertArrayEquals(expected, actual);
	}

	@ParameterizedTest
	@ValueSource(strings = {"ABC", "1", "A"})
	void paramsTest(String param) {
		assertTrue(param.length() > 0);
	}

	@ParameterizedTest
	@CsvSource(value = { "abcd, ABCD", "abc, ABC", "a, A", "ab, AB" })
	void upperCaseParameterized(String word, String capitalizedWord) {
		assertEquals(capitalizedWord, word.toUpperCase());
	}

	@ParameterizedTest(name = "{0} length is {1}")
	@CsvSource(value = {"abcd, 4", "abc, 3", "ab, 2", "'', 0"})
	void lengthParameterized(String word, int length) {
		assertEquals(word.length(), length);
	}

	@Test
	@Disabled
	void performanceTest() {
		assertTimeout(Duration.ofSeconds(4), () -> {
			for (int i = 0; i < 1000000; i++) {
				int j = i;
				System.out.println(j);
			}
		});
	}

	@Nested
	class EmptyStringTest {

		String str;

		@BeforeEach
		void setup() {
			str = "";
		}

		@Test
		void lengthIsZero() {
			assertEquals(0, str.length());
		}

		@Test
		void uppercaseIsEmpty() {
			assertEquals("", str.toUpperCase());
		}

	}
}
