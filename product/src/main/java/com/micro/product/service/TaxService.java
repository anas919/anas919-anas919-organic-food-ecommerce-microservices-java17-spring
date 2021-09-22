package com.micro.product.service;

import com.micro.product.dto.TaxRequest;
import com.micro.product.dto.TaxResponse;
import com.micro.product.model.Tax;
import com.micro.product.repository.TaxRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaxService {

    private final TaxRepository taxRepository;

    public TaxService(TaxRepository taxRepository) {
        this.taxRepository = taxRepository;
    }


    public void createTax(TaxRequest taxRequest) {
        Tax tax = new Tax();
        tax.setName(taxRequest.getName());

        taxRepository.save(tax);
    }

    @Transactional(readOnly = true)
    public List<TaxResponse> getAllTaxes() {
        List<Tax> taxs = (List<Tax>) taxRepository.findAll();
        return taxs.stream().map(tax -> mapToTaxResponse(tax)).toList();
    }

    private TaxResponse mapToTaxResponse(Tax tax) {
        TaxResponse taxResponse = new TaxResponse();
        taxResponse.setId(tax.getId());
        taxResponse.setName(tax.getName());
        return taxResponse;
    }
}
