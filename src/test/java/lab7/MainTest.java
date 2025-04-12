package lab7;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    // ---- Tests for getQuickRecipes ----

    @Test
    void testGetQuickRecipesReturnsEmptyListIfNoData() {
        var recipes = Main.getQuickRecipes(List::of);
        assertEquals(0, recipes.size());
    }

    @Test
    void testGetQuickRecipesReturnsEmptyListIfNoQuickRecipes() {
        var recipes = Main.getQuickRecipes(() -> List.of(
                new Recipe(0, "", "", "", 4, 10, 10, 16),
                new Recipe(1, "", "", "", 4, 10, 10, 20),
                new Recipe(2, "", "", "", 4, 10, 10, 200)
        ));
        assertEquals(0, recipes.size());
    }

    @Test
    void testGetQuickRecipesReturnsAllRecipesIfAllQuick() {
        var recipes = Main.getQuickRecipes(() -> List.of(
                new Recipe(0, "", "", "", 4, 10, 10, 15),
                new Recipe(1, "", "", "", 4, 10, 10, 1),
                new Recipe(2, "", "", "", 4, 10, 10, 10)
        ));
        assertEquals(3, recipes.size());
    }

    @Test
    void testGetQuickRecipesWorksOnTypicalData() {
        var recipes = Main.getQuickRecipes(() -> List.of(
                new Recipe(0, "", "", "", 4, 10, 10, 10),
                new Recipe(1, "", "", "", 4, 10, 10, 15),
                new Recipe(2, "", "", "", 4, 10, 10, 16),
                new Recipe(3, "", "", "", 4, 10, 10, 20),
                new Recipe(4, "", "", "", 4, 10, 10, 2343)
        ));

        assertEquals(2, recipes.size());
        assertEquals(0, recipes.get(0).id());
        assertEquals(1, recipes.get(1).id());
    }

    // ---- Tests for searchRecipes ----

    @Test
    void testSearchRecipesFindsMatchByName() {
        DataService mock = () -> List.of(
                new Recipe(1, "Chicken Tacos", "Yum", "", 4, 10, 10, 30),
                new Recipe(2, "Pasta", "Cheesy and good", "", 4, 10, 10, 25)
        );
        var results = Main.searchRecipes("chicken", mock);
        assertEquals(1, results.size());
        assertEquals(1, results.get(0).id());
    }

    @Test
    void testSearchRecipesFindsMatchByDescription() {
        DataService mock = () -> List.of(
                new Recipe(1, "Tacos", "With chicken and spice", "", 4, 10, 10, 30),
                new Recipe(2, "Burger", "Beef", "", 4, 10, 10, 25)
        );
        var results = Main.searchRecipes("chicken", mock);
        assertEquals(1, results.size());
        assertEquals(1, results.get(0).id());
    }

    @Test
    void testSearchRecipesIsCaseInsensitive() {
        DataService mock = () -> List.of(
                new Recipe(1, "CHICKEN Wings", "Hot and spicy", "", 4, 10, 10, 30)
        );
        var results = Main.searchRecipes("chicken", mock);
        assertEquals(1, results.size());
    }

    @Test
    void testSearchRecipesReturnsEmptyWhenNoMatch() {
        DataService mock = () -> List.of(
                new Recipe(1, "Tofu Stir Fry", "Vegan meal", "", 4, 10, 10, 20)
        );
        var results = Main.searchRecipes("chicken", mock);
        assertTrue(results.isEmpty());
    }

    @Test
    void testSearchRecipesReturnsEmptyOnError() {
        DataService mock = () -> {
            throw new DataService.DataException(new Exception("Simulated failure"));
        };
        var results = Main.searchRecipes("chicken", mock);
        assertNotNull(results);
        assertTrue(results.isEmpty());
    }
}
