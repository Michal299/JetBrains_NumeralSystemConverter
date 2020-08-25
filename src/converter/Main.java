package converter;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int sourceRadix = scanner.nextInt();
        String number = scanner.next();
        int targetRadix = scanner.nextInt();

        Converter converter = new Converter();

        String targetRepresentation;
        if (number.contains(".")) {
            double decimalRepresentation = converter.convertToDoubleDecimal(number, sourceRadix);
            targetRepresentation = converter.convertDecimalTo(decimalRepresentation, targetRadix);
        } else {
            long decimalRepresentation = converter.convertToLongDecimal(number, sourceRadix);
            targetRepresentation = converter.convertDecimalTo(decimalRepresentation, targetRadix);
        }


        System.out.println(targetRepresentation);


    }
}
