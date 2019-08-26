import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

/*
    Simple Junit Parameterized test for Calculator.class
 */

@DisplayName("Simple unit for  Calculator class")
@RunWith(Parameterized.class)
public class CalculatorTest {

    private String toCalculateExpression;
    private int expectedResult;
    public CalculatorTest(String toCalculateExpression, int expectedResult) {
        this.toCalculateExpression = toCalculateExpression;
        this.expectedResult = expectedResult;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"(add 1 1)", 2},
                {"(add 0 (add 3 4))", 7},
                {"(add 3 (add (add 3 3) 3))", 12},
                {"(multiply 1 1)", 1},
                {"(multiply 0 (multiply 3 4))", 0},
                {"(multiply 2 (multiply 3 4))", 24},
                {"(multiply 3 (multiply (multiply 3 3) 3))", 81},
                {"(add 1 (multiply 2 3))", 7},
                {"(multiply 2 (add (multiply 2 3) 8))", 28},
                {"123", 123},
                {"0", 0}
        });
    }

    @Test
    public void test_add_multiple() {
        assertEquals(expectedResult, Calculator.evaluate(toCalculateExpression.toUpperCase()));
        System.out.println(String.format("%s", toCalculateExpression));
        System.out.println(String.format(" => %d", expectedResult));
        System.out.println("__________________________________________________________");
    }

    @Test(expected = NumberFormatException.class)
    public void test_negative_scenario(){
        ArrayList<String> list=new ArrayList<String>();
        list.add("I am good"); //Invalid String
        list.add("(multiply 2 (add (multiply 2 3) 8)"); // Missing closing brackets
        list.add("(multiply 2 (add (multiply 2 3) -8"); // Invalid expression in between
        list.add("(multiply 2 (add ( 2 3) -8"); // Missing operator
        list.add("%901"); // Invalid operation with "%" Operator
        for (int i = 0 ; i< list.size(); i++){
            Calculator.evaluate(list.get(i).toUpperCase());
        }
    }
}
