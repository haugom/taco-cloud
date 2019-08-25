package tacos

import lombok.extern.slf4j.Slf4j
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import java.util.*

@Slf4j
@Controller
@RequestMapping("/design")
class DesignTacoController {

    @GetMapping
    fun showDesignForm(model: Model): String {
        val ingredients = Arrays.asList(
                Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP),
                Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP),
                Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN),
                Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN),
                Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES),
                Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES),
                Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE),
                Ingredient("JACK", "Monterry Jack", Ingredient.Type.CHEESE),
                Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE),
                Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE)
        )
        Ingredient.Type.values().forEach {
            model.addAttribute(it.toString().toLowerCase(),
                    Filter.filterByType(ingredients, it))
        }
        model.addAttribute("design", Taco("your name", mutableListOf<Ingredient>()))
        return "design"
    }

}