package lab.proj.utils;

import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * A debug printer implementation that prints log messages with indentation.
 */
public class IndentedDebugPrinter implements DebugPrinter {

    public static Object MAIN = new Object();
    private static IndentedDebugPrinter instance;
    private final PrintStream outputStream;
    private final Map<Object, String> objectNameMap = new HashMap<>();
    private final Deque<Object> objectStack = new ArrayDeque<>();
    private int indentation = 0;

    private IndentedDebugPrinter(PrintStream outputStream) {
        this.outputStream = outputStream;
        objectNameMap.put(MAIN, "Main");
        objectStack.offerLast(MAIN);
    }

    /**
     * Retrieves the singleton instance of IndentedDebugPrinter.
     * @return The singleton instance of IndentedDebugPrinter.
     */
    public static IndentedDebugPrinter getInstance() {
        if (instance == null)
            instance = new IndentedDebugPrinter(System.out);
        return instance;
    }

    /**
     * Resets the singleton instance of IndentedDebugPrinter with a target stream.
     * @param targetStream The target stream to which debug messages will be printed.
     * @return The reset singleton instance of IndentedDebugPrinter.
     */
    public static IndentedDebugPrinter resetInstance(PrintStream targetStream) {
        instance = new IndentedDebugPrinter(targetStream);
        return instance;
    }

    /**
     * Retrieves the name of an object.
     * @param object The object for which to retrieve the name.
     * @return The name of the object.
     */
    public String getObjectName(Object object) {
        if (object == null)
            return "null";
        if (object instanceof Collection<?> collection)
            return String.format("[%s]",
                    collection.stream().map(this::getObjectName).collect(Collectors.joining(", ")));
        return objectNameMap.getOrDefault(object, object.toString());
    }

    @Override
    public void createObject(Object createdObject, String nameOfCreatedObject) {
        Object creator = objectStack.peekLast();

        printIndentations();
        outputStream.printf("%s created %s of type %s%n",
                objectNameMap.getOrDefault(creator, "Unknown"),
                nameOfCreatedObject,
                createdObject.getClass().getSimpleName()
        );

        objectNameMap.put(createdObject, nameOfCreatedObject);
    }

    @Override
    public void destroyObject(Object destroyedObject) {
        Object destroyer = objectStack.peekLast();

        printIndentations();
        outputStream.printf("%s destroyed %s%n",
                objectNameMap.getOrDefault(destroyer, "Unknown"),
                objectNameMap.get(destroyedObject));

        objectNameMap.remove(destroyedObject);
    }

    private String getCallerMethodName() {
        List<StackTraceElement> stackTraceElements = Arrays.asList(Thread.currentThread().getStackTrace());
        stackTraceElements.remove(0);
        final String currentLogger = stackTraceElements.get(0).getClassName();
        return stackTraceElements.stream()
                .filter(ste -> !currentLogger.equals(ste.getClassName()))
                .findFirst().orElseThrow().getMethodName();
    }

    @Override
    public void invokeObjectMethod(Object callee, List<?> params) {
        Object caller = objectStack.peekLast();
        if (caller == callee) {
            selfInvokeMethod(params);
            return;
        }

        objectStack.offerLast(callee);

        printIndentations();
        outputStream.printf("--> %s.%s(%s)%n", objectNameMap.get(callee), getCallerMethodName(),
                params.stream().map(this::getObjectName).collect(Collectors.joining(", ")));

        indentation++;
    }

    @Override
    public void selfInvokeMethod(List<?> params) {
        Object object = objectStack.peekLast();
        objectStack.offerLast(object);

        printIndentations();
        outputStream.printf("--| %s.%s(%s)%n", objectNameMap.get(object), getCallerMethodName(),
                params.stream().map(this::getObjectName).collect(Collectors.joining(", ")));
        printIndentations();
        outputStream.println("<-|");

        indentation++;
    }

    @Override
    public void returnFromMethod(Optional<Object> returnValue) {
        indentation--;

        printIndentations();
        outputStream.printf("<-- %s%n", returnValue.map(this::getObjectName).orElse(""));

        objectStack.pollLast();
    }

    private void printIndentations() {
        for (int i = 0; i < indentation; i++) {
            outputStream.print('\t');
        }
    }
}
