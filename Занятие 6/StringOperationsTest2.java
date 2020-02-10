package example;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class StringOperationsTest2 {

    StringOperations stringOperations = new StringOperations();
    String nullPointer;

    @Test
    public void testIsEqual() {
        boolean result = stringOperations.isEqual("java", "java");
        Assert.assertTrue(result);
    }

    @DataProvider
    public Object[][] getStrings() {
        return new Object[][]{
                {"java", "Java"},
                {"java", "avaj"}
        };
    }

    @Test(dataProvider = "getStrings")
    public void testIsEqualNegative(String str1, String str2) {
        boolean result = stringOperations.isEqual(str1, str2);
        Assert.assertFalse(result);
    }

    @Test
    public void testStart() {
        boolean result = stringOperations.start("hello, world", "hello,");
        Assert.assertTrue(result);
    }

    @Test
    public void testStartNegative() {
        boolean result = stringOperations.start("hello, world", nullPointer + "hello,");
        Assert.assertFalse(result);
    }

    @Test
    public void testStartNegative2() {
        boolean result = stringOperations.start("hello, world", " hello,");
        Assert.assertFalse(result);
    }

    @Test
    public void testEnd() {
        boolean result = stringOperations.end("hello, world", "world");
        Assert.assertTrue(result);
    }

    @Test
    public void testEndNegative() {
        boolean result = stringOperations.end("hello, world", "world" + nullPointer);
        Assert.assertFalse(result);
    }

    @Test
    public void testEndNegative2() {
        boolean result = stringOperations.end("hello, world", "world ");
        Assert.assertFalse(result);
    }
}