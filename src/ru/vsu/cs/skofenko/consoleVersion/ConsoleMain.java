package ru.vsu.cs.skofenko.consoleVersion;

import ru.vsu.cs.skofenko.common.NotTriangle;
import ru.vsu.cs.skofenko.common.Triangle;
import ru.vsu.cs.skofenko.common.Logic;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Locale;

public class ConsoleMain {

    public static void main(String[] args) {
        Locale.setDefault(Locale.ROOT);
        try {
            InputArgs inputArgs = parseCmdArgs(args);
            List<Triangle> inputList = Logic.readListFromFile(inputArgs.getInputFile());
            List<List<Triangle>> answer = Logic.findSimilarTriangles(inputList);
            Logic.writeAnswerToFile(inputArgs.getOutputFile(), answer);
        } catch (DoublePathException e) {
            System.err.println("Путь к " + e.getOfWhat() + " введён два раза");
            System.exit(-2);
        } catch (FileNotFoundException | NullPointerException e) {
            System.err.println("Файл ввода вывода не найден");
            System.exit(-3);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException| NotTriangle e) {
            System.err.println("Неверный список на вход");
            System.exit(-4);
        }
    }

    public static InputArgs parseCmdArgs(String[] args) throws DoublePathException {
        InputArgs inputArgs = new InputArgs();
        if (args.length == 2 && !args[0].startsWith("-i") && !args[0].startsWith("-o") && !args[0].startsWith("--input-file=")
                && !args[0].startsWith("–-output-file=") && !args[1].startsWith("-i") && !args[1].startsWith("-o") &&
                !args[1].startsWith("--input-file=") && !args[1].startsWith("–-output-file=")) {
            inputArgs.setInputFile(args[0]);
            inputArgs.setOutputFile(args[1]);
        } else {
            for (String arg : args) {
                if (arg.startsWith("--input-file=")) {
                    if (inputArgs.getInputFile() != null)
                        throw new DoublePathException("input");
                    else
                        inputArgs.setInputFile(arg.substring("--input-file=".length()));
                } else if (arg.startsWith("–-output-file=")) {
                    if (inputArgs.getOutputFile() != null)
                        throw new DoublePathException("output");
                    else
                        inputArgs.setOutputFile(arg.substring("–-output-file=".length()));
                } else if (arg.startsWith("-i")) {
                    if (inputArgs.getInputFile() != null)
                        throw new DoublePathException("input");
                    else inputArgs.setInputFile(arg.substring(3));
                } else if (arg.startsWith("-o")) {
                    if (inputArgs.getOutputFile() != null)
                        throw new DoublePathException("output");
                    else inputArgs.setOutputFile(arg.substring(3));
                }
            }
        }
        return inputArgs;
    }
}