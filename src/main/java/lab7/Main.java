package lab7;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    /**
     * Get all recipes that can be made in 15 minutes or less
     * @param dataService a service that provides access to recipe data
     * @return a list of quick recipes
     */
    public static List<Recipe> getQuickRecipes(DataService dataService) {
        try {
            var recipes = dataService.getRecipes();
            return recipes.stream().filter(r -> r.totalTime() <= 15).toList();
        } catch (Exception e) {
            logger.error("Error while getting quick recipes: " + e.getMessage());
            logger.debug("Stack trace: " + Arrays.toString(e.getStackTrace()));
            return List.of(); // Return an empty list if error
        }
    }

    /**
     * Search for recipes whose name or description contains the given search term (case insensitive)
     * @param searchTerm the term to search for
     * @param dataService a service that provides access to recipe data
     * @return a list of matching recipes
     */
    public static List<Recipe> searchRecipes(String searchTerm, DataService dataService) {
        try {
            var recipes = dataService.getRecipes();
            return recipes.stream()
                    .filter(r ->
                            r.name().toLowerCase().contains(searchTerm.toLowerCase()) ||
                                    r.description().toLowerCase().contains(searchTerm.toLowerCase())
                    )
                    .toList();
        } catch (Exception e) {
            logger.error("Error while searching recipes: " + e.getMessage());
            logger.debug("Stack trace: " + Arrays.toString(e.getStackTrace()));
            return List.of(); // Return an empty list if error
        }
    }

    public static void main(String[] args) {
        // INJECT SqliteDataService (implements DataService interface)
        var dataService = new CsvDataService();


        // Print quick recipes (<= 15 min)
        var quickRecipes = getQuickRecipes(dataService);
        System.out.println("Quick Recipes:");
        quickRecipes.forEach(System.out::println);

        // Use the searchRecipes method with the same data service
        var searchResults = searchRecipes("chicken", dataService);
        System.out.println("\nSearch Results for 'chicken':");
        searchResults.forEach(System.out::println);
    }
}
