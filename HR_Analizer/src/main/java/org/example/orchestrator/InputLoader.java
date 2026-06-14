package org.example.orchestrator;

import lombok.Builder;
import lombok.Getter;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


@Getter
@Builder
public class InputLoader {

    String command;
    Date from;
    Date to;
    String user;
    Path path;


    public static InputLoader create(String[] args) {

        String c = null;
        Date f = null;
        Date t = null;
        String u = null;
        Path p = null;

        List<String> arglist = new ArrayList<String>();
        arglist.addAll(Arrays.asList(args));
        c = arglist.remove(0);
        for (String a : arglist) {

            if (a.charAt(1) == 'f') {
                f = new Date(a.substring(2));
            }
            if (a.charAt(1) == 't') {
                t = new Date(a.substring(2));
            }
            if (a.charAt(1) == 'u') {
                u = a.substring(2);
            }
            if (a.charAt(1) == 'p') {
                p = Path.of(a.substring(2));
            }

        }

        return InputLoader.builder().command(c).from(f).to(t).user(u).path(p).build();

    }


}

