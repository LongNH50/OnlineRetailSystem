package miu.edu.onlineRetailSystem.service;

import miu.edu.onlineRetailSystem.contact.AddressTypeResponse;
import miu.edu.onlineRetailSystem.domain.AddressType;
import miu.edu.onlineRetailSystem.repository.AddressTypeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AddressTypeServiceImpl implements AddressTypeService{

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private AddressTypeRepository addressTypeRepository;

    @Override
    public void save(AddressTypeResponse addressTypeResponse) {

        AddressType addressType = mapper.map(addressTypeResponse, AddressType.class);

        addressTypeRepository.save(addressType);

    }

    @Override
    public AddressTypeResponse update(int addressTypeId, AddressTypeResponse addressTypeResponse) {

        AddressType addressType = mapper.map(addressTypeResponse, AddressType.class);


        addressTypeRepository.save(addressType);


            return null;

    }
}
