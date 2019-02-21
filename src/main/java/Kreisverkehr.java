import java.util.HashMap;
import java.util.Map;

public final class Kreisverkehr {
    private final Map<String, String> parsedArguments = new HashMap<>();

    private void badArgument(String arg) {
        System.err.println("Don't know what to do with argument '" + arg + "'. Exiting.");
        System.exit(1);
    }

    private void parseArgs(String[] args) {
        Map<String, String> argumentAbbreviations = new HashMap<>();
        argumentAbbreviations.put("c", "config");
        argumentAbbreviations.put("o", "output");
        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            String argumentName = "";
            String argumentValue = "";
            if (arg.startsWith("--") && arg.length() > 2 && arg.charAt(2) != '-') {
                argumentName = arg.substring(2);
            } else if (arg.startsWith("-") && arg.length() == 2) {
                String abbreviation = arg.substring(1);
                if (argumentAbbreviations.containsKey(abbreviation)) {
                    argumentName = argumentAbbreviations.get(abbreviation);
                } else {
                    badArgument(arg);
                }
            } else {
                badArgument(arg);
            }

            if (i + 1 < args.length && !args[i + 1].startsWith("-")) {
                argumentValue = args[i + 1];
                i++;
            }
            parsedArguments.put(argumentName, argumentValue);
        }
    }

    private void run(String[] args) {
        parseArgs(args);
        String config = parsedArguments.getOrDefault("config", "");
        String output = parsedArguments.getOrDefault("output", "");
        new KreisverkehrRunner().run(config, output);
    }
    public static void main(String[] args) {
        new Kreisverkehr().run(args);
    }
}