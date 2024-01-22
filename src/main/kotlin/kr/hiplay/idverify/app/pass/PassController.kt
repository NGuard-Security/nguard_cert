package kr.hiplay.idverify.app.pass

import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.LocaleResolver


@Controller
@RequestMapping("/pass")
class PassController {
    @Autowired
    private lateinit var request: HttpServletRequest

    @Autowired
    @Qualifier("localeResolver")
    private lateinit var localeResolver: LocaleResolver

    private lateinit var passService: PassService

    @GetMapping("/start.html")
    fun start(model: Model): String {
        model["provider"] = "pass"
        model["lang"] = localeResolver.resolveLocale(request)

        return "identify/start"
    }

    @GetMapping("/request.html")
    fun request(model: Model): String {

        val orderId = passService.createOrder()
        val hashData = passService.getHash(orderId)

        model["orderId"] = orderId
        model["hashData"] = hashData

        return "identify/pass/request"
    }
}
