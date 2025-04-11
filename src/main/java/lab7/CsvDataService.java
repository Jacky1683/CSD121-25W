package lab7;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;

public class CsvDataService implements DataService {

    private static final String CSV_FILE_PATH = "recipes.csv"; // must be in root of project

    @Override
    public List<Recipe> getRecipes() throws DataException {
        try (var reader = new FileReader(CSV_FILE_PATH)) {
            List<RecipeCSV> csvRecipes = new CsvToBeanBuilder<RecipeCSV>(reader)
                    .withType(RecipeCSV.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build()
                    .parse();

            // Map CSV beans to Recipe records
            return csvRecipes.stream()
                    .map(r -> new Recipe(r.getName(), r.getDescription(), Integer.parseInt(r.getTotalTime())))
                    .collect(Collectors.toList());

        } catch (Exception e) {
            throw new DataException(e);
        }
    }
}
