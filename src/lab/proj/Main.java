package lab.proj;

import lab.proj.utils.ActionManager;
import lab.proj.utils.SequenceDiagramPrinter;
import java.io.OutputStream;
import java.io.PrintStream;


public class Main {
    public static void main(String[] args) {
        testModel();
    }

    private static void testModel() {
        // Suppress output from debug printer
        SequenceDiagramPrinter.resetInstance(new PrintStream(OutputStream.nullOutputStream()));
        // Run interpreter loop
        new ActionManager(System.in, System.out).runCommandInterpreter();
    }

//    private static void testUseCases() {
//        SequenceDiagramPrinter.resetInstance(System.out);
//
//        final List<String> availableUseCases = TestUseCaseRunner.getAvailableUseCases();
//
//        StringBuilder questionBuilder = new StringBuilder("Melyik Use-Case-t futtassuk?\n");
//        for (int i = 1; i <= availableUseCases.size(); i++)
//            questionBuilder.append(String.format("\t%d. %s%n", i, availableUseCases.get(i - 1)));
//        String question = questionBuilder.toString();
//
//        int selectedUseCase = AskTheUser.number(question) - 1;
//        TestUseCaseRunner.runTest(selectedUseCase);
//    }
}