package lab7;

import com.opencsv.bean.CsvBindByName;

public class RecipeCSV {

    @CsvBindByName(column = "name")
    private String name;

    @CsvBindByName(column = "description")
    private String description;

    @CsvBindByName(column = "totalTime")
    private String totalTime;

    // Getters are required by OpenCSV
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getTotalTime() {
        return totalTime;
    }
}
