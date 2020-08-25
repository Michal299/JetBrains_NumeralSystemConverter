package converter;

public class Converter {

    public String convertDecimalTo(long number, int numSys) {
        StringBuilder result = new StringBuilder();

        if (number == 0) {
            return "0";
        }

        while (number > 0) {

            if (numSys == 1) {
                result.append(1);
                number--;
            } else {
                result.append(getSymbol(number % numSys));
                number = number / numSys;
            }

        }
        return result.reverse().toString();
    }

    public String convertDecimalTo(double number, int numSys) {
        int integer = (int) number;
        double fractional = number - integer;

        StringBuilder result = new StringBuilder();
        result.append(convertDecimalTo(integer, numSys));
        result.append(".");

        for (int i = 0; i < 5; i++) {
            fractional *= numSys;
            int num = (int) (fractional);
            fractional -= num;
            result.append(getSymbol(num));
        }

        return result.toString();
    }

    public long convertToLongDecimal(String number, int base) {
        long result = 0;
        char digit;
        long pow = 1;

        if (number.length() == 1) {
            return dispatchSymbol(number.charAt(0));
        }

        for (int i = number.length() - 1; i >= 0; i--) {
            digit = number.charAt(i);
            result += (dispatchSymbol(digit) * pow);
            pow *= base;
        }

        return result;
    }

    public double convertToDoubleDecimal(String number, int base) {
        String[] parts = number.split("\\.");
        long integerPart = convertToLongDecimal(parts[0], base);

        double result = 0;
        result += integerPart;

        long power = base;
        for (int i = 0; i < parts[1].length(); i++) {
            result += (double) (dispatchSymbol(parts[1].charAt(i))) / power;
            power *= base;
        }

        return result;
    }

    public String getPrefixByBase(int base) {
        switch (base) {
            case 2:
                return "0b";
            case 8:
                return "0";
            case 16:
                return "0x";
            default:
                return "";
        }
    }

    private char getSymbol(long rest) {
        if (rest < 10) {
            return (char)(rest + '0');
        } else {
            return (char)('a' + rest - 10);
        }
    }

    private int dispatchSymbol(char symbol) {
        if (symbol >= '0' && symbol <= '9') {
            return symbol - '0';
        } else {
            return 10 + symbol - 'a';
        }
    }
}