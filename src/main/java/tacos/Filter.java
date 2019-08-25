package tacos;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.stream.Collectors;

public class Filter {
    @Nullable
    public static List<Ingredient> filterByType(@NotNull List<Ingredient> ingredients, @NotNull Ingredient.Type it) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(it))
                .collect(Collectors.toList());
    }
}
