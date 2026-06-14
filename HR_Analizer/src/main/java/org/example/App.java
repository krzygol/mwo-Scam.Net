package org.example;

import org.example.model.DataModel;
import org.example.orchestrator.InputLoader;
import org.example.orchestrator.Orchestrator;
import org.example.reader.ReaderXLSX;

public class App {

    public void run(String[] args) throws Exception {

        //        Path path = Path.of("reporter-dane");

        InputLoader inputLoader = InputLoader.create(args);

        ReaderXLSX reader = new ReaderXLSX();
        DataModel data = reader.importAll(inputLoader.getPath());


        Orchestrator orchestrator = new Orchestrator(data, inputLoader);

//        if (inputLoader.getFrom() == null) {
//            inputLoader.setFrom(data.getMinDate());
//        }
//
//        if (inputLoader.getTo() == null) {
//            inputLoader.setTo(data.getMaxDate());
//        }


        orchestrator.controller();





    }
}
