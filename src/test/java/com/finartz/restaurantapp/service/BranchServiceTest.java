package com.finartz.restaurantapp.service;


import com.finartz.restaurantapp.model.Address;
import com.finartz.restaurantapp.model.Branch;
import com.finartz.restaurantapp.model.City;
import com.finartz.restaurantapp.model.County;
import com.finartz.restaurantapp.model.enumerated.Status;
import com.finartz.restaurantapp.repository.BranchRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class BranchServiceTest {

    @InjectMocks
    private BranchService branchService;

    @Mock
    private BranchRepository branchRepository;


    @Test
    public void whenFetchAll_thenReturnAllBranch() {
        Branch branch1 = Branch.builder().id(1l).name("Kral Burger Ümraniye").build();
        Branch branch2 = Branch.builder().id(2l).name("Kral Burger Avcılar").build();
        List<Branch> branchList = Arrays.asList(branch1, branch2);

        Mockito.when(branchRepository.findAll()).thenReturn(branchList);

        List<Branch> fetchedList = branchService.getAll();

        assertEquals(branchList.size(), fetchedList.size());
    }

    @Test
    public void whenFetchById_thenReturnBranch() {
        Branch branch = Branch.builder().name("Kral Burger Ümraniye").build();

        Mockito.when(branchRepository.getById(1L)).thenReturn(branch);

        Branch fetchedBranch = branchService.getById(1L);

        assertEquals(branch.getId(), fetchedBranch.getId());
    }

    @Test
    public void whenAddBranch_thenReturnSavedBranch() {
        Branch branch = Branch.builder().name("Kral Burger Ümraniye").build();

        Mockito.doReturn(branch).when(branchRepository).save(branch);

        Branch returnedBranch = branchService.create(branch);

        assertEquals(branch.getName(), returnedBranch.getName());
    }

    @Test
    public void whenUpdateBranch_thenReturnUpdatedBranch(){
        Branch branch = Branch.builder().name("Kral Burger Ümraniye").build();

        Mockito.when(branchRepository.save(branch)).thenReturn(branch);

        Branch updatedBranch = branchService.update(branch);

        assertEquals(branch , updatedBranch);

    }

    @Test
    public void whenFetchedByStatus_thenReturnSomeBranches() {
        Branch branch1 = Branch.builder().status(Status.WAITING).build();
        Branch branch2 = Branch.builder().status(Status.WAITING).build();
        Branch branch3 = Branch.builder().status(Status.WAITING).build();
        List<Branch> branchList = Arrays.asList(branch1,branch2,branch3);

        Mockito.when(branchRepository.findByStatus(Status.WAITING)).thenReturn(branchList);

        List<Branch> fetchedList = branchService.findByStatus(Status.WAITING);

        assertEquals(branchList, fetchedList);
    }

    @Test
    public void whenFetchedByAddress_County_Id_thenReturnSomeBranches() {
        City city = City.builder().name("İstanbul").build();
        County county = County.builder().city(city).name("Ümraniye").build();
        Address address = Address.builder().city(city).county(county).build();
        Branch branch1 = Branch.builder().build();
        Branch branch2 = Branch.builder().build();
        Branch branch3 = Branch.builder().build();
        branch1.setAddress(address);
        branch2.setAddress(address);
        branch3.setAddress(address);
        List<Branch> branchList = Arrays.asList(branch1,branch2,branch3);

        Mockito.when(branchRepository.findByAddress_County_Id(county.getId())).thenReturn(branchList);

        List<Branch> fetchedList = branchService.findByAddress_County_Id(county.getId());

        assertEquals(fetchedList, branchList);
    }

}
