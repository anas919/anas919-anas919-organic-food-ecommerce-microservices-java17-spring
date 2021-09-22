package com.micro.product.service;

import com.micro.product.dto.VendorRequest;
import com.micro.product.dto.VendorResponse;
import com.micro.product.model.Vendor;
import com.micro.product.repository.VendorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VendorService {

    private final VendorRepository vendorRepository;

    public VendorService(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }


    public void createVendor(VendorRequest vendorRequest) {
        Vendor vendor = new Vendor();
        vendor.setName(vendorRequest.getName());

        vendorRepository.save(vendor);
    }

    @Transactional(readOnly = true)
    public List<VendorResponse> getAllVendors() {
        List<Vendor> vendors = (List<Vendor>) vendorRepository.findAll();
        return vendors.stream().map(vendor -> mapToVendorResponse(vendor)).toList();
    }

    private VendorResponse mapToVendorResponse(Vendor vendor) {
        VendorResponse vendorResponse = new VendorResponse();
        vendorResponse.setId(vendor.getId());
        vendorResponse.setName(vendor.getName());
        return vendorResponse;
    }
}
