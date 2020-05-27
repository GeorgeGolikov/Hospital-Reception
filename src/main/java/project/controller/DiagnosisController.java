package project.controller;

import project.entity.Diagnosis;
import project.service.DiagnosisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/diagnosis")
public class DiagnosisController
{
    @Autowired
    private DiagnosisService diagnosisService;

    @GetMapping("/diagnosisShowAll")
    public ResponseEntity<List<Diagnosis>> getAllDiagnosis()
    {
        List<Diagnosis> list = diagnosisService.listOfDiagnosis();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/diagnosisShow/{id}")
    public ResponseEntity<Diagnosis> getDiagnosis(@PathVariable(name = "id") int id)
    {
        Diagnosis diagnosis = diagnosisService.findDiagnosis(id);
        if (diagnosis != null) return new ResponseEntity<>(diagnosis, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/addDiagnosis")
    public ResponseEntity<?> addDiagnosis(@RequestBody Diagnosis diagnosis)
    {
        diagnosisService.addDiagnosis(diagnosis);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteDiagnosis")
    public ResponseEntity<?> deleteDiagnosis(@RequestBody Diagnosis diagnosis)
    {
        final boolean deleted = diagnosisService.deleteDiagnosis(diagnosis);
        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PutMapping("/editDiagnosis/{id}")
    public ResponseEntity<?> editDiagnosis(@PathVariable(name = "id") int id, @RequestBody Diagnosis diagnosis)
    {
        final boolean updated = diagnosisService.editDiagnosis(id, diagnosis);
        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
