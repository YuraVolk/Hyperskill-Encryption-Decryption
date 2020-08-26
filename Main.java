package encryptdecrypt;

import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            List<String> list = Arrays.asList(args);

            String choice;
            String input;
            int shift;
            String algorithm;

            if (list.contains("-mode")) {
                choice = list.get(list.indexOf("-mode") + 1);
            } else {
                choice = "enc";
            }

            if (list.contains("-key")) {
                shift = Integer.parseInt(list.get(list.indexOf("-key") + 1), 10);
            } else {
                shift = 0;
            }

            if (list.contains("-data")) {
                input = list.get(list.indexOf("-data") + 1);
            } else if (list.contains("-in")) {
                String file = list.get(list.indexOf("-in") + 1);
                input = String.join("", Files.readAllLines(
                        new File(file).toPath()));
            } else {
                input = "";
            }

            if (list.contains("-alg")) {
                algorithm = list.get(list.indexOf("-alg") + 1);
            } else {
                algorithm = "shift";
            }

            StringBuilder builder = new StringBuilder();

            if (choice.equals("enc")) {
                if (shift > 26) {
                    shift = shift / (shift % 26);
                }

                if (algorithm.equals("shift")) {
                    for (int i = 0; i < input.length(); i++) {
                        if (Character.isLetter(input.charAt(i))) {


                            char j = (char) (input.charAt(i) + shift);


                            if (Character.isLowerCase(input.charAt(i))) {
                                if (j > 122) {
                                    j = (char) (97 + (j - 123));
                                }

                            } else {

                                if (j > 90) {

                                    j = (char) (65 + (j - 91));
                                }

                            }

                            builder.append(j);
                        } else {
                            builder.append(input.charAt(i));
                        }

                    }
                } else if (algorithm.equals("unicode")) {
                    for (int i = 0; i < input.length(); i++) {
                        char j = (char) (input.charAt(i) + shift);
                        builder.append(j);
                    }
                }
            } else if (choice.equals("dec")) {
                if (shift > 26) {
                    shift = shift / (shift % 26);
                }


                if (algorithm.equals("shift")) {
                    for (int i = 0; i < input.length(); i++) {
                        if (Character.isLetter(input.charAt(i))) {

                            char j = (char) (input.charAt(i) - shift);
                            if (Character.isLowerCase(input.charAt(i))) {
                                if (j < 97) {
                                    j = (char) (123 - (97 - j));
                                }

                            } else {

                                if (j < 65) {
                                    j = (char) (91 - (65 - j));
                                }

                            }

                            builder.append(j);
                        } else {
                            builder.append(input.charAt(i));
                        }

                    }
                } else if (algorithm.equals("unicode")) {
                    for (int i = 0; i < input.length(); i++) {
                        char j = (char) (input.charAt(i) - shift);
                        builder.append(j);
                    }
                }

            }

            if (list.contains("-out")) {
                PrintWriter writer = new PrintWriter(
                        list.get(list.indexOf("-out") + 1), "UTF-8");
                writer.print(builder.toString());
                writer.close();
            } else {
                System.out.println(builder.toString());
            }
        } catch (Exception e) {
            System.out.println("Error");
        }

        //  System.out.println("dv ulfmw z givzhfiv!");
    }
}
