package com.example.PetPalHub.Controller;

import com.example.PetPalHub.Dto.ApplicationDto;
import com.example.PetPalHub.Dto.FilterDto;
import com.example.PetPalHub.Dto.PetHeaderDto;
import com.example.PetPalHub.Dto.PetViewDto;
import com.example.PetPalHub.Services.AdopterService;
import com.example.PetPalHub.Services.FilterService;
import com.example.PetPalHub.Services.MasterControlServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/adopter/")
@CrossOrigin("*")
public class MasterController {

    @Autowired
    private MasterControlServices masterControlServices;
    @Autowired
    private FilterService filterService;

    @PostMapping("acceptApplication/{petId}/{adopterId}")
    public ResponseEntity<Void> acceptApplication(@PathVariable int petId, @PathVariable int adopterId) {
        masterControlServices.acceptApplication(adopterId, petId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("rejectApplication/{petId}/{adopterId}")
    public ResponseEntity<Void> rejectApplication(@PathVariable int petId, @PathVariable int adopterId) {
        masterControlServices.rejectApplication(adopterId, petId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("getPendingApplicationByShelterID/{pageIndex}/{pageSize}/{shelterId}")
    public ResponseEntity<List<ApplicationDto>> getPendingApplicationByShelterID(@PathVariable int pageIndex, @PathVariable int pageSize, @PathVariable int shelterId) {
        List<ApplicationDto> applicationDtoList = masterControlServices.getPendingApplicationByShelterID(pageIndex, pageSize, shelterId);
        return new ResponseEntity<>(applicationDtoList, HttpStatus.OK);
    }

    @GetMapping("getAcceptedApplicationByShelterID/{pageIndex}/{pageSize}/{shelterId}")
    public ResponseEntity<List<ApplicationDto>> getAcceptedApplicationByShelterID(@PathVariable int pageIndex, @PathVariable int pageSize, @PathVariable int shelterId) {
        List<ApplicationDto> applicationDtoList = masterControlServices.getAcceptedApplicationByShelterID(pageIndex, pageSize, shelterId);
        return new ResponseEntity<>(applicationDtoList, HttpStatus.OK);
    }

    @GetMapping("getRejectedApplicationByShelterID/{pageIndex}/{pageSize}/{shelterId}")
    public ResponseEntity<List<ApplicationDto>> getRejectedApplicationByShelterID(@PathVariable int pageIndex, @PathVariable int pageSize, @PathVariable int shelterId) {
        List<ApplicationDto> applicationDtoList = masterControlServices.getRejectedApplicationByShelterID(pageIndex, pageSize, shelterId);
        return new ResponseEntity<>(applicationDtoList, HttpStatus.OK);
    }


    @GetMapping("getFilteredPetsHeadersListByShelterId/{shelterId}/{pageIndex}/{pageSize}")
    public ResponseEntity<List<PetHeaderDto>> getFilteredPetsHeadersListByShelterId(@PathVariable int shelterId, @PathVariable int pageIndex, @PathVariable int pageSize, @RequestBody FilterDto filterDto) {
        List<PetHeaderDto> petHeaderDtos = filterService.getFilteredPetsHeadersListByShelterId(shelterId, pageIndex, pageSize, filterDto.getFilters());
        return new ResponseEntity<>(petHeaderDtos, HttpStatus.OK);
    }


    @PostMapping("addPet")////check here
    public ResponseEntity<Void> addPet(@RequestBody PetViewDto petViewDto) {
        masterControlServices.addPet(petViewDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("editPet")////check here
    public ResponseEntity<Void> editPet(@RequestBody PetViewDto petViewDto) {
        masterControlServices.editPet(petViewDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}