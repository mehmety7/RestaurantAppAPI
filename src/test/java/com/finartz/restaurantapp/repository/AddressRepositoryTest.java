package com.finartz.restaurantapp.repository;

import com.finartz.restaurantapp.model.entity.AddressEntity;
import com.finartz.restaurantapp.model.entity.UserEntity;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@DataJpaTest
public class AddressRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private AddressRepository addressRepository;

    @Test
    public void whenGetAddressEntitiesByUserEntityId_thenReturnAddressEntities(){
        // Cause user who has id 1L has not address, it was possible to add new address record!
        AddressEntity addressEntity = new AddressEntity();
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        addressEntity.setUserEntity(userEntity);
        addressEntity.setName("Address");
        testEntityManager.persist(addressEntity);
        testEntityManager.flush();

        List<AddressEntity> foundAddressEntity = addressRepository.getAddressEntitiesByUserEntityId(1L);

        Assertions.assertEquals(foundAddressEntity.get(0).getName(), addressEntity.getName());
    }

    @Test
    public void whenGetAddressEntityByBranchEntityId(){
        AddressEntity foundAddressEntity = addressRepository.getAddressEntityByBranchEntityId(2L);
        Assertions.assertEquals(2L, foundAddressEntity.getBranchEntity().getId());
    }

    @Test
    public void whenGetActiveAddressByUserId_thenReturnAddressEntity(){
        AddressEntity addressEntity = addressRepository.getActiveAddressByUserId(3L);

        Assertions.assertEquals(3L, addressEntity.getUserEntity().getId());
    }

}
