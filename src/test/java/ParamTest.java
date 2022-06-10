import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.time.Month;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParamTest {
    /**
     * Checking if the number is palindrome
     */
    public boolean isPalindrome(String p) {
        return p != null && StringUtils.reverse(p).equals(p);
    }

    @ParameterizedTest(name = "{index} - {0} is a palindrome")
    @ValueSource(strings = {"deified", "deed", "sagas"})
    void testPalindrome(String palindrome) {
        assertTrue(isPalindrome(palindrome));
    }

    /**
     * Checking if the number is odd number
     */
    public boolean isOddNumber(int number) {
        return number % 2 != 0;
    }

    @Disabled
    @ParameterizedTest(name = "{index} - {0} is odd number")
    @ValueSource(ints = {9, 11, 8, 84, 145681})
    void checkIfNumberOddNumber(int number) {
        assertTrue(isOddNumber(number));
    }

    /**
     * Checking the information about person using @CsvSource
     */
    @ParameterizedTest(name = "{index} - the name {0} starts with A and the age is {1}")
    @CsvSource({
            "Alex, 36",
            "Artem, 56",
            "Annika, 29",
            "Albert, 18"
    })
    void testWithCsvTest(String name, int age) {
        assertTrue(name.startsWith("A"));
        assertTrue(age >= 18);
    }

    /**
     * Checking all month numbers using @EnumSource
     */
    @EnumSource(Month.class)
    @ParameterizedTest(name = "{index} - Getting month {0}")
    void getMonthValue(Month month) {
        int monthNumber = month.getValue();
        assertTrue(monthNumber >= 1 && monthNumber <= 12);
    }

/**Checking if value contains the needed letters using @MethodSource*/
    @MethodSource(value = "provideCheckForEndsWith")
    @ParameterizedTest(name = "{index} - the value {0} is {1}")
    void checkMethod(String input, boolean result){
        String ends = "ad";
        assertEquals(result, input.endsWith(ends));

    }
    public static Stream<Arguments> provideCheckForEndsWith() {
        return Stream.of(
                Arguments.of("bad", true),
                Arguments.of("sad", true),
                Arguments.of("red", false)
        );
    }
}


