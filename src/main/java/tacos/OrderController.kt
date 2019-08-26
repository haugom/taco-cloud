package tacos

import mu.KotlinLogging
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import javax.validation.Valid

@Controller
@RequestMapping("/orders")
class OrderController {

    private val logger = KotlinLogging.logger {}

    @ModelAttribute
    fun order(model: Model) {
        model.addAttribute("order", Order())
    }

    @GetMapping("/current")
    fun orderForm(model: Model): String {
        return "orderForm"
    }

    @PostMapping
    fun processOrder(@Valid @ModelAttribute("order") order: Order, errors: Errors): String {

        if (errors.hasErrors()) {
            return "orderForm";
        }

        logger.run { info("Order submitted: $order") }
        return "redirect:/"
    }
}