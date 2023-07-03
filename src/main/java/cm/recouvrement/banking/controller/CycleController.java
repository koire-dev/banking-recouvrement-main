package cm.recouvrement.banking.controller;

import cm.recouvrement.banking.entity.Client;
import cm.recouvrement.banking.entity.Cycle;
import cm.recouvrement.banking.repository.CycleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CycleController {

    @Autowired
    private CycleRepository cycleRepository;

    @PostMapping("/addCycle")
    public ResponseEntity<Cycle> saveClient(@RequestBody Cycle cycle){

        Cycle saveCycle = cycleRepository.save(cycle);
        return new ResponseEntity<>(saveCycle, HttpStatus.OK);
    }

    @GetMapping("/findAllCycle")
    public List<Cycle> getAllCycle(){
        return cycleRepository.findAll();
    }

    @GetMapping("/findCycleById/{id}")
    public ResponseEntity<Cycle> getCycleById(@PathVariable("id") Long id){

        Cycle cycle = cycleRepository.findById(id).get();

        return new ResponseEntity<>(cycle, HttpStatus.OK);
    }
}
