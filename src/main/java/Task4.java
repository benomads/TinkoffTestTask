import java.util.Scanner;

public class Task4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String expression = scanner.nextLine();

        try {
            long result = evaluateExpression(expression);
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("WRONG");
        }
    }

    public static long evaluateExpression(String expression) throws Exception {
        int len = expression.length();
        char[] expChars = expression.toCharArray();

        long[] operands = new long[len / 2 + 1];
        char[] operators = new char[len / 2];
        int opIndex = 0;
        int oprIndex = 0;

        for (int i = 0; i < len; i++) {
            char ch = expChars[i];

            if (ch == '(') {
                operators[oprIndex++] = ch;
            } else if (Character.isDigit(ch)) {
                long num = 0;
                while (i < len && Character.isDigit(expChars[i])) {
                    num = num * 10 + (expChars[i] - '0');
                    i++;
                }
                i--;

                operands[opIndex++] = num;
            } else if (ch == ')') {
                while (oprIndex > 0 && operators[oprIndex - 1] != '(') {
                    operands[opIndex - 2] = applyOperator(operators[--oprIndex], operands[opIndex - 1], operands[opIndex - 2]);
                    opIndex--;
                }
                oprIndex--; // Pop '('
            } else if (ch == '+' || ch == '-' || ch == '*') {
                while (oprIndex > 0 && hasHigherPrecedence(ch, operators[oprIndex - 1])) {
                    operands[opIndex - 2] = applyOperator(operators[--oprIndex], operands[opIndex - 1], operands[opIndex - 2]);
                    opIndex--;
                }
                operators[oprIndex++] = ch;
            }
        }

        while (oprIndex > 0) {
            operands[opIndex - 2] = applyOperator(operators[--oprIndex], operands[opIndex - 1], operands[opIndex - 2]);
            opIndex--;
        }

        return operands[0];
    }

    public static boolean hasHigherPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')') {
            return false;
        }
        return (op1 != '*' && op1 != '/') || (op2 != '+' && op2 != '-');
    }

    public static long applyOperator(char operator, long b, long a) throws Exception {
        return switch (operator) {
            case '+' -> a + b;
            case '-' -> a - b;
            case '*' -> a * b;
            default -> throw new Exception("Invalid operator");
        };
    }
}