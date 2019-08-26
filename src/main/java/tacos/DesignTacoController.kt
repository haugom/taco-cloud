package tacos

import mu.KotlinLogging
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import java.util.*
import javax.validation.Valid

@Controller
@RequestMapping("/design")
class DesignTacoController {

    private val logger = KotlinLogging.logger {}

    @ModelAttribute
    fun addIngredientsToModel(model: Model) {
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
    }

    @ModelAttribute
    fun taco(model: Model) {
        model.addAttribute("design", Taco())
    }

    @GetMapping
    fun showDesignForm(model: Model): String {
        return "design"
    }

    @PostMapping
    fun processDesign(@Valid @ModelAttribute("design") design: Taco, errors: Errors): String {

        logger.run { info("$design") }

        if (errors.hasErrors()) {
            return "design";
        }

        // TODO: save the taco design
        logger.run { info("Processing design: $design") }
        return "redirect:/orders/current"
    }

}