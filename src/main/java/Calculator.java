import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    A simple calculator: it takes a single argument as an expression and prints out the integer result of evaluating it.
    Note : This program is to set in console mode and has while(true) loop for execution, so break or stop application to end
 */
public class Calculator {
    public static void main(String[] args) throws IOException {
        while (true) {
            System.out.println("Enter A Valid Expression To Calculate: ");

            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(System.in));
            String expression = reader.readLine();
            try {
                System.out.println("=> " + evaluate(expression.toUpperCase()));
            } catch (Exception e) {
                System.out.println("Input expression is invalid");
            }
        }
    }

    public static int evaluate(String expr) {
        // remove any trailing spaces
        expr = expr.trim();

        // remove the outer braces for processing. ex: (ADD 1 1) -> ADD 1 1
        if (expr.startsWith("(")) {
            expr = expr.substring(1, expr.length() - 1).trim();
        }
        String a;
        String b;
        String operation = null;
        if (expr.startsWith("ADD")) {
            operation = "ADD";
        } else if (expr.startsWith("MULTIPLY")) {
            operation = "MULTIPLY";
        }

        // No operation found, should be just an integer. ex: "245"
        if (operation == null) {
            return Integer.parseInt(expr);
        }

        // If there is no sub-exp to evalute. ex: "ADD 1 1"
        if (expr.indexOf("(") == -1) {
            StringTokenizer str = new StringTokenizer(expr, " ");
            str.nextToken();
            a = str.nextToken();
            b = str.nextToken();
        } else {
            // There is sub-exp to evalute. ex: "ADD 1 (MULTIPLY 2 3)"
            // Find the sub-exp and evalute it before evaluating the outer exp
            // ex: evalute("MULTIPLY 2 3") and then evalute("ADD 1 <RESULT_OF_SUB_EXP_EVALUTION>")
            a = "" + evaluate(expr.substring(expr.indexOf("(") + 1, expr.lastIndexOf(")"))) + "";
            b = expr.substring(expr.lastIndexOf(")") + 1);
            if (b.length() == 0) {
                // parameter is at front. ex: ADD 1 (MUTLIPLY 2 3)
                b = expr.substring("ADD ".length(), expr.indexOf("(")).trim();
                try {
                    int number = Integer.parseInt(b);
                } catch (Exception e) {
                    b = expr.substring("MULTIPLY ".length(), expr.indexOf("(")).trim();
                }
            } else {
                // parameter is at end. ex: ADD (MUTLIPLY 2 3) 1
                b = expr.substring(expr.lastIndexOf(")") + 1).trim();
            }
        }

        if (operation.contains("ADD")) {
            return add(Integer.parseInt(a), Integer.parseInt(b));
        } else if (operation.contains("MULTIPLY")) {
            return multiply(Integer.parseInt(a), Integer.parseInt(b));
        }
        return Integer.parseInt(expr);
    }

    private static int multiply(int a, int b) {
        return a * b;
    }

    private static int add(int a, int b) {
        return a + b;
    }
}
