package dd.projects.ddshop.controllers;

import java.util.HashMap;
import java.util.Map;

import dd.projects.ddshop.entities.CheckoutPayment;
import org.springframework.web.bind.annotation.*;

import com.google.gson.Gson;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class StripeController {
    private static Gson gson = new Gson();
    @PostMapping("/payment")
    /**
     * Payment with Stripe checkout page
     *
     * @throws StripeException
     */
    public String paymentWithCheckoutPage(@RequestBody CheckoutPayment payment) throws StripeException {
        init();
        SessionCreateParams params = SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT).setSuccessUrl(payment.getSuccessUrl())
                .setCancelUrl(
                        payment.getCancelUrl())
                .addLineItem(
                        SessionCreateParams.LineItem.builder().setQuantity(payment.getQuantity())
                                .setPriceData(
                                        SessionCreateParams.LineItem.PriceData.builder()
                                                .setCurrency(payment.getCurrency()).setUnitAmount(payment.getAmount())
                                                .setProductData(SessionCreateParams.LineItem.PriceData.ProductData
                                                        .builder().setName(payment.getName()).build())
                                                .build())
                                .build())
                .build();
        Session session = Session.create(params);
        Map<String, String> responseData = new HashMap<>();
        responseData.put("id", session.getId());
        return gson.toJson(responseData);
    }

    private static void init() {
        Stripe.apiKey = "sk_test_51NMG3SEamICphCUsdey65jbUElbECQemOgt659e7Ch3m9plErn9R47norJTT4DOtqcVdSTrlGpk0NFl0iF4Rxa8i005vqT0doq";
    }
}
