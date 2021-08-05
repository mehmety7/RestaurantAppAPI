package com.finartz.restaurantapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.finartz.restaurantapp.model.converter.dto.AddressDtoConverter;
import com.finartz.restaurantapp.model.dto.AddressDto;
import com.finartz.restaurantapp.model.entity.AddressEntity;
import com.finartz.restaurantapp.service.AddressService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
@WebMvcTest(AddressController.class)
public class AddressControllerTest {

    private static final String URI_ADDRESS = "/address";
    private static final String CITY_ISTANBUL = "İstanbul";
    private static final String COUNTY_AVCILAR = "Avcılar";
    private static final String DISTRICT_MERKEZ = "Merkez";
    private static final String DISTRICT_UNIVERSITE = "Üniversite";
    private static final String NAME_EV = "Ev";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddressService addressService;

    @MockBean
    private AddressDtoConverter addressDtoConverter;

    @MockBean
    private UserDetailsService userDetailsService;

    private ObjectWriter objectWriter;

    @Before
    public void init() {
        // to-do : https://stackoverflow.com/questions/20504399/testing-springs-requestbody-using-spring-mockmvc
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        objectWriter = mapper.writer().withDefaultPrettyPrinter();

    }

    @Test
    public void whenGetAllAddress_thenReturnAddress() throws Exception {

        AddressEntity addressEntity = AddressEntity.builder().name(NAME_EV).district(DISTRICT_MERKEZ).build();
        AddressDto address = AddressDto.builder().name(NAME_EV).district(DISTRICT_MERKEZ).build();

        Mockito.when(addressDtoConverter.convert(addressEntity)).thenReturn(address);

        List<AddressEntity> addressEntities = Arrays.asList(addressEntity);
        List<AddressDto> addresses = new ArrayList<>();
        addressEntities.forEach(addressEntity1 -> {
            addresses.add(addressDtoConverter.convert(addressEntity1));
        });

        Mockito.when(addressService.getAddresses()).thenReturn(addresses);

        mockMvc.perform(get(URI_ADDRESS)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].name", Matchers.is(NAME_EV)));
    }

//    @Test
//    public void whenGetByAddressId_thenReturnAddress() throws Exception {
//
//        CityEntity cityEntity = CityEntity.builder().name(CITY_ISTANBUL).build();
//
//        CountyEntity countyEntity = CountyEntity.builder().name(COUNTY_AVCILAR).cityEntity(cityEntity).build();
//
//        AddressEntity addressEntity = AddressEntity.builder().countyEntity(countyEntity).cityEntity(cityEntity).name(NAME_EV).district(DISTRICT_MERKEZ).id(1L).build();
//
//        Mockito.when(addressService.getAddress(1L)).thenReturn(addressEntity);
//
//        mockMvc.perform(get(URI_ADDRESS + "/1")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("name", Matchers.is(NAME_EV)));
//
//    }
//
//    @Test
//    public void whenCreateNewAddress_thenReturnCreated() throws Exception {
//
//        AddressEntity addressEntity = AddressEntity.builder().name(NAME_EV).district(DISTRICT_MERKEZ).id(1L).build();
//
//        Mockito.when(addressService.createAddress(addressEntity)).thenReturn(addressEntity);
//
//        String requestJson = objectWriter.writeValueAsString(addressEntity);
//
//        mockMvc.perform(post(URI_ADDRESS)
//                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.name", Matchers.is(NAME_EV)));
//
//    }
//
//    @Test
//    public void whenUpdateExistsAddress_thenReturnUpdated() throws Exception {
//
//        AddressEntity addressEntity = AddressEntity.builder().name(NAME_EV).district(DISTRICT_MERKEZ).id(1L).build();
//
//        AddressEntity modifyAddressEntity = AddressEntity.builder().district(DISTRICT_UNIVERSITE).id(1l).build();
//
//        Mockito.when(addressService.createAddress(addressEntity)).thenReturn(addressEntity);
//        Mockito.when(addressService.updateAddress(modifyAddressEntity)).thenReturn(modifyAddressEntity);
//
//        String requestJson1 = objectWriter.writeValueAsString(addressEntity);
//        String requestJson2 = objectWriter.writeValueAsString(modifyAddressEntity);
//
//        mockMvc.perform(post(URI_ADDRESS)
//                .contentType(MediaType.APPLICATION_JSON).content(requestJson1))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.id", Matchers.is(1)));
//
//        mockMvc.perform(put(URI_ADDRESS)
//                .contentType(MediaType.APPLICATION_JSON).content(requestJson2))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id", Matchers.is(1)));
//
//    }
//
//    @Test
//    public void whenDeleteExistsAddress_thenReturnOk() throws Exception {
//
//        CityEntity cityEntity = CityEntity.builder().name(CITY_ISTANBUL).build();
//
//        CountyEntity countyEntity = CountyEntity.builder().name(COUNTY_AVCILAR).cityEntity(cityEntity).build();
//
//        AddressEntity addressEntity = AddressEntity.builder().countyEntity(countyEntity).cityEntity(cityEntity).name(NAME_EV).district(DISTRICT_MERKEZ).id(1L).build();
//
//        Mockito.when(addressService.deleteAddress(1L)).thenReturn(addressEntity);
//
//        mockMvc.perform(delete(URI_ADDRESS + "/1")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//    }

}
