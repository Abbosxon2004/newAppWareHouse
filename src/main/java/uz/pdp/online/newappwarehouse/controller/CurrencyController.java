package uz.pdp.online.newappwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.newappwarehouse.entity.Currency;
import uz.pdp.online.newappwarehouse.payload.Result;
import uz.pdp.online.newappwarehouse.service.CurrencyService;

@RestController
@RequestMapping(value = "/currency")
public class CurrencyController {
    @Autowired
    CurrencyService currencyService;

    @PostMapping
    public Result addCurrency(Currency currency) {
        final Result result = currencyService.addCurrency(currency);
        return result;
    }

    @GetMapping
    public Result getCurrencies() {
        final Result resultCurrencies = currencyService.getCurrencies();
        return resultCurrencies;
    }

    @GetMapping("/currencyId/{id}")
    public Result getCurrencyById(@PathVariable Integer id) {
        final Result resultCurrencyById = currencyService.getCurrencyById(id);
        return resultCurrencyById;
    }

    @PutMapping("/currencyId/{id}")
    public Result editCurrency(@PathVariable Integer id, Currency currency) {
        final Result result = currencyService.editCurrency(id, currency);
        return result;
    }

    @DeleteMapping("/currencyId/{id}")
    public Result deleteCurrency(@PathVariable Integer id) {
        final Result result = currencyService.deleteCurrency(id);
        return result;
    }

}
