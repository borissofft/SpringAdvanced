package bg.softuni.kidscare.service;

import bg.softuni.kidscare.model.entity.PartnerEntity;
import bg.softuni.kidscare.model.service.PartnerServiceModel;
import bg.softuni.kidscare.model.view.PartnerViewModel;
import bg.softuni.kidscare.repository.PartnerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartnerService {
    private final PartnerRepository partnerRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PartnerService(PartnerRepository partnerRepository, ModelMapper modelMapper) {
        this.partnerRepository = partnerRepository;
        this.modelMapper = modelMapper;
    }


    public void addPartner(PartnerServiceModel partnerServiceModel) {
        this.partnerRepository
                .save(this.modelMapper.map(partnerServiceModel, PartnerEntity.class));
    }

    public List<PartnerViewModel> findAllPartners() {
        return this.partnerRepository.findAll()
                .stream()
                .map(partnerEntity -> this.modelMapper.map(partnerEntity, PartnerViewModel.class))
                .toList();
    }
}
