package uz.pdp.online.newappwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.online.newappwarehouse.entity.Currency;
import uz.pdp.online.newappwarehouse.payload.Result;
import uz.pdp.online.newappwarehouse.repository.CurrencyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CurrencyService {
    @Autowired
    CurrencyRepository currencyRepository;

    public Result addCurrency(Currency currency){
        final boolean existsByName = currencyRepository.existsByName(currency.getName());
        if (existsByName)
            return new Result("This currency name already exists",false);
        currencyRepository.save(currency);
        return new Result("Currency added",true);
    }

    public Result getCurrencies(){
        final List<Currency> currencyList = currencyRepository.findAll();
        return new Result("Currency list found",true,currencyList);
    }

    public Result getCurrencyById(Integer id){
        final Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        if (!optionalCurrency.isPresent())
            return new Result("Currency not found",false);
        final Currency currency = optionalCurrency.get();
        return new Result("Currency found.",true,currency);
    }

    public Result editCurrency(Integer id,Currency currency){
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        if (!optionalCurrency.isPresent())
            return new Result("Currency id not found",false);
        Currency editingCurrency = optionalCurrency.get();
        final boolean existsByName = currencyRepository.existsByName(currency.getName());
        if (existsByName)
            return new Result("This already exists",false);
        editingCurrency.setName(currency.getName());
        return new Result("Currency edited.",true);
    }

    public Result deleteCurrency(Integer id){
        currencyRepository.deleteById(id);
        return new Result("Currency deleted",true);
    }

}
