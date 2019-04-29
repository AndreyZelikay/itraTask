package hello.controllers;

import com.stripe.model.Charge;
import hello.service.StripeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/payment")
@CrossOrigin
public class PaymentController {

    private StripeClient stripeClient;

    @Autowired
    PaymentController(StripeClient stripeClient) {
        this.stripeClient = stripeClient;
    }

    @PostMapping("")
    public Charge chargeCard(@RequestBody Map map) throws Exception {
        String token = map.get("token").toString();
        Integer amount = Integer.parseInt(map.get("amount").toString());
       // Integer amount = Integer.parseInt(request.getHeader("amount"));
        return this.stripeClient.chargeCreditCard(token, amount);
    }
}
