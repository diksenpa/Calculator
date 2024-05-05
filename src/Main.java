//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.Scanner;

public class Main {
    public Main() {
    }

    public static void main(String[] args) throws Exception {
        String[] roman_numbers = new String[]{"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        int[] arabic_numbers = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        char[] operands = new char[]{'+', '-', '*', '/'};
        System.out.print("Введите пример: ");
        Scanner sc = new Scanner(System.in);
        String s = Remove_Spaces(sc.nextLine());
        sc.close();
        int operator_position = -1;
        boolean is_roman = false;

        for(int i = 0; i < operands.length; ++i) {
            if (operator_position == -1) {
                operator_position = s.indexOf(operands[i]);
            }
        }

        if (operator_position == -1) {
            Ex("Оператор не найден");
        }

        String sa = "";
        String sb = "";
        String so = "";
        if (operator_position > 0 && s.length() >= 3) {
            sa = s.substring(0, operator_position);
            sb = s.substring(operator_position + 1);
            so = s.substring(operator_position, operator_position + 1);
            Integer a = 0;
            Integer b = 0;
            Integer ab = 0;
            if (check_the_numbers(roman_numbers, arabic_numbers, sa) != check_the_numbers(roman_numbers, arabic_numbers, sb)) {
                Ex("Разные системы счисления не допустимы");
            }

            if (check_the_numbers(roman_numbers, arabic_numbers, sa) == 0) {
                a = Integer.parseInt(sa);
                b = Integer.parseInt(sb);
            } else {
                is_roman = true;

                for(int i = 0; i < roman_numbers.length; ++i) {
                    if (sa.equalsIgnoreCase(roman_numbers[i])) {
                        a = arabic_numbers[i];
                    }

                    if (sb.equalsIgnoreCase(roman_numbers[i])) {
                        b = arabic_numbers[i];
                    }
                }
            }

            ab = Calculate(a, b, so);
            clearScreen();
            System.out.print(sa + " " + so + " " + sb + " = ");
            if (is_roman) {
                System.out.print(Convert_to_roman(roman_numbers, ab));
            } else {
                System.out.print(ab);
            }
        } else {
            Ex("Пример введен не верно");
        }

    }

    public static int check_the_numbers(String[] acceptable_roman_numbers, int[] acceptable_arabic_numbers, String number) throws Exception {
        int result = -1;

        int i;
        for(i = 0; i < acceptable_roman_numbers.length; ++i) {
            if (result == -1 && number.equalsIgnoreCase(acceptable_roman_numbers[i])) {
                result = 1;
            }
        }

        if (result == -1) {
            for(i = 0; i < acceptable_arabic_numbers.length; ++i) {
                if (result == -1 && number.equals(String.valueOf(acceptable_arabic_numbers[i]))) {
                    result = 0;
                }
            }
        }

        if (result == -1) {
            Ex("Введено не верное число");
        }

        return result;
    }

    public static String Convert_to_roman(String[] _roman_numbers, Integer _integer) throws Exception {
        String result = "";
        Integer ost = _integer;
        if (ost <= 0) {
            Ex("Число равно или меньше чем 0");
        }

        if (ost / 100 == 1) {
            result = result + "C";
            ost = ost - 100;
        }

        if (ost / 90 == 1) {
            result = result + "XC";
            ost = ost - 90;
        }

        if (ost / 50 == 1) {
            result = result + "L";
            ost = ost - 50;
        }

        if (ost / 40 == 1) {
            result = result + "XL";
            ost = ost - 40;
        }

        for(int i = 0; i < ost / 10; ost = ost - 10) {
            result = result + "X";
        }

        if (ost > 0) {
            result = result + _roman_numbers[ost - 1];
        }

        return result;
    }

    public static Integer Calculate(Integer a, Integer b, String operator) {
        Integer var10000;
        switch (operator) {
            case "+" -> var10000 = a + b;
            case "-" -> var10000 = a - b;
            case "*" -> var10000 = a * b;
            case "/" -> var10000 = a / b;
            default -> var10000 = 0;
        }

        return var10000;
    }

    public static void clearScreen() {
        System.out.print("\u001b[H\u001b[2J");
        System.out.flush();
    }

    public static String Remove_Spaces(String s) {
        String result = s;
        result = result.replaceAll(" ", "");
        return result;
    }

    public static void Ex() throws Exception {
        throw new Exception("Ошибка");
    }

    public static void Ex(String error_description) throws Exception {
        throw new Exception(error_description);
    }
}
