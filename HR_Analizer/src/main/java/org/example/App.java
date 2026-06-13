package org.example;

import org.example.display.Displayer;
import org.example.model.DataModel;
import org.example.reader.ReaderXLSX;
import org.example.report.*;

public class App {

    public void run(String[] args) {
        String filePath = args.length > 0 ? args[0] : "data.xlsx";

        ReaderXLSX reader = new ReaderXLSX();
        DataModel data = reader.read(filePath);

        Displayer displayer = new Displayer();

        displayer.display(new Report1SumAllUsers(data));
        displayer.display(new Report2SumAllProjects(data));
        displayer.display(new Report4Top10Tasks(data));
        displayer.display(new Report3UsersAllProjects(data));
        displayer.display(new Report5UsersMaxTimeLoad(data));
    }
}
