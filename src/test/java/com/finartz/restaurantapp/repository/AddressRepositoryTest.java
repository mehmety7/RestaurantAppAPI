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
import org.springframework.test.context.TestPropertySource;
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
    public void whenGetAddressEntitiesByUserEntity_Id_thenReturnAddressEntities(){
        // Cause user who has id 1L has not address, it was possible to add new address record!
        AddressEntity addressEntity = new AddressEntity();
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1l);
        addressEntity.setUserEntity(userEntity);
        addressEntity.setName("Address");
        testEntityManager.persist(addressEntity);
        testEntityManager.flush();

        List<AddressEntity> foundAddressEntity = addressRepository.getAddressEntitiesByUserEntity_Id(1l);

        Assertions.assertEquals(foundAddressEntity.get(0).getName(), addressEntity.getName());
    }

    @Test
    public void whenGetAddressEntityByBranchEntity_Id(){
        AddressEntity foundAddressEntity = addressRepository.getAddressEntityByBranchEntity_Id(2l);
        Assertions.assertEquals(foundAddressEntity.getBranchEntity().getId(), 2l);
    }

    @Test
    public void whenGetActiveAddressByUserId_thenReturnAddressEntity(){
        AddressEntity addressEntity = addressRepository.getActiveAddressByUserId(3l);

        Assertions.assertEquals(addressEntity.getUserEntity().getId(), 3l);
    }

}
