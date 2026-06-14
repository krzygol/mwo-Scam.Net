package org.example.report;

import org.example.display.model.Report0MetadataData;
import org.example.model.DataModel;
import org.example.orchestrator.InputLoader;

import java.text.SimpleDateFormat;

public class Report0Metadata extends Report<Report0MetadataData> {

    private final DataModel dataModel;
    private final InputLoader inputLoader;

    public Report0Metadata(DataModel data, InputLoader inputLoader) {
        super(data, inputLoader);
        this.dataModel = data;
        this.inputLoader = inputLoader;
    }

    @Override
    public Report0MetadataData generate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        return new Report0MetadataData(
                sdf.format(inputLoader.getFrom()),
                sdf.format(inputLoader.getTo()),
                dataModel.getMinDate() != null ? sdf.format(dataModel.getMinDate()) : null,
                dataModel.getMaxDate() != null ? sdf.format(dataModel.getMaxDate()) : null,
                dataModel.getUserCount(),
                dataModel.getProjectCount(),
                dataModel.getTaskCount(),
                dataModel.getTotalDuration(),
                dataModel.getAvgDurationPerUser(),
                dataModel.getAvgDurationPerProject()
        );
    }
}