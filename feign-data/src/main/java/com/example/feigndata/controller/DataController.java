package com.example.feigndata.controller;

import com.example.feigndata.dto.DataRequestDTO;
import com.example.feigndata.dto.DataResponseDTO;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/data")

public class DataController {
    private Map<Long, DataResponseDTO> dataStore = new HashMap<>();
    private Long idCnt = 1L;
    @PostConstruct
    public void initDataSource(){
        dataStore.put(idCnt++, new DataResponseDTO(1L,"Item 1",100));
        dataStore.put(idCnt++, new DataResponseDTO(1L,"Item 1",100));
        dataStore.put(idCnt++, new DataResponseDTO(1L,"Item 1",100));
        dataStore.put(idCnt++, new DataResponseDTO(1L,"Item 1",100));
        dataStore.put(idCnt++, new DataResponseDTO(1L,"Item 1",100));
    }

    @GetMapping("/{id}")
    public DataResponseDTO getData(@PathVariable Long id){
        log.info("[Feign Data] select");
        return dataStore.get(id);
    }

    @PostMapping
    public DataResponseDTO createData(@RequestBody DataRequestDTO dataRequestDTO){
        log.info("[Feign Data] create");
        DataResponseDTO newData = DataResponseDTO.builder()
                .id(idCnt)
                .name(dataRequestDTO.getName())
                .value(dataRequestDTO.getValue())
                .build();
        dataStore.put(idCnt++, newData);
        return newData;
    }

    @PutMapping("/{id}")
    public DataResponseDTO updateData(
            @PathVariable Long id,
            @RequestBody DataRequestDTO dataRequestDTO
    ){
        log.info("[Feign Data] update");
        DataResponseDTO dataResponseDTO = dataStore.get(id);
        System.out.println(dataResponseDTO.getName());
        System.out.println(dataResponseDTO.getValue());
        dataResponseDTO.setName(dataRequestDTO.getName());
        dataResponseDTO.setValue(dataRequestDTO.getValue());
        dataStore.put(id, dataResponseDTO);
        return dataResponseDTO;
    }

    @DeleteMapping("/{id}")
    public String deleteData(@PathVariable Long id){
        log.info("[Feign Data] delete");

        dataStore.remove(id);
        for(int i=0; i<dataStore.size(); i++){
            System.out.println(dataStore.get(i));
        }
        return "Excute delete";
    }

    @GetMapping
    public ArrayList<DataResponseDTO> getData(){
        ArrayList<DataResponseDTO> arrayList = new ArrayList<>();
        for(Long i = 1L; i<= dataStore.size(); i++){
            arrayList.add(dataStore.get(i));
        }
        log.info("[Feign Data] all select");
        return arrayList;
    }

    // 강사님 코드
    @GetMapping("/all")
    public List<DataResponseDTO> getAll(){
        log.info("[Feign Data] getAll");
        List<DataResponseDTO> results = new ArrayList<>();
        for(Long id : dataStore.keySet()){
            DataResponseDTO dataResponseDTO = dataStore.get(id);
            results.add(dataResponseDTO);
        }
        return results;
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        log.info("[Feign Data] delete");
        DataResponseDTO removed = dataStore.remove(id);
        if(removed != null){
            // 예외처리
        }
        return "Data with ID" + removed.getId() + " was deleted";
    }
}
