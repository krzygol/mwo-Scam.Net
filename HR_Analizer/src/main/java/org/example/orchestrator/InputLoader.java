package org.example.orchestrator;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@Builder
public class InputLoader {

    String command;
    Date from;
    Date to;
    String user;
    Path path;
    Boolean print;
    String project;

    public static InputLoader create(String[] args) throws ParseException {
        if (args == null || args.length == 0) {
            throw new IllegalArgumentException("No arguments provided. At least a command name is required.");
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);

        String c = null;
        Date f = sdf.parse("2000-05-01");
        Date t = sdf.parse("2050-01-01");
//        Date f = null;
//        Date t = null;
        String u = null;
        Path p = null;
        Boolean x = false;
        String j = null;

        List<String> arglist = new ArrayList<>(Arrays.asList(args));
        c = arglist.remove(0);

        if (c == null || c.isBlank()) {
            throw new IllegalArgumentException("Command name cannot be empty.");
        }

        for (String a : arglist) {
            if (a == null || a.length() < 3) {
                throw new IllegalArgumentException(
                        "Invalid argument: '" + a + "'. Expected format: -Xvalue (e.g. -f2024-01-01).");
            }
            if (a.charAt(0) != '-') {
                throw new IllegalArgumentException(
                        "Argument '" + a + "' must start with '-'.");
            }

            char flag = a.charAt(1);
            String value = a.substring(3);

            switch (flag) {
                case 'f':
                    try {
                        f = sdf.parse(value);
                    } catch (ParseException e) {
                        throw new IllegalArgumentException(
                                "Invalid 'from' date (-f): '" + value + "'. Expected format: yyyy-MM-dd.", e);
                    }
                    break;
                case 'x':
                    if (value.equals("x")) {
                        x = true;
                        break;
                    }
                case 'j':
                    j = value;

            break;

                case 't':
                    try {
                        t = sdf.parse(value);
                    } catch (ParseException e) {
                        throw new IllegalArgumentException(
                                "Invalid 'to' date (-t): '" + value + "'. Expected format: yyyy-MM-dd.", e);
                    }
                    break;
                case 'u':
                    if (value.isBlank()) {
                        throw new IllegalArgumentException("User (-u) cannot be empty.");
                    }
                    u = value;
                    break;
                case 'p':
                    try {
                        p = Path.of(value);
                    } catch (java.nio.file.InvalidPathException e) {
                        throw new IllegalArgumentException(
                                "Invalid path (-p): '" + value + "'.", e);
                    }
                    break;
                default:
                    throw new IllegalArgumentException(
                            "Unknown flag: '-" + flag + "'. Available flags: -f, -t, -u, -p.");
            }
        }

        if (f.after(t)) {
            throw new IllegalArgumentException(
                    "Start date '" + sdf.format(f) + "' cannot be after end date '" + sdf.format(t) + "'.");
        }

        return InputLoader.builder().command(c).from(f).to(t)
                .user(u + ".xlsx").path(p).print(x).project(j).build();
    }
}