package project.controller;

import project.entity.Ward;
import project.service.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wards")
public class WardController
{
    @Autowired
    private WardService wardService;

    @GetMapping("/wardsShowAll")
    public ResponseEntity<List<Ward>> getAllWards()
    {
        List<Ward> list = wardService.listOfWards();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/wardsShowAll/{id}")
    public ResponseEntity<Ward> getWard(@PathVariable(name = "id") int id)
    {
        Ward ward = wardService.findWard(id);
        if (ward != null) return new ResponseEntity<>(ward, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/addWard")
    public ResponseEntity<?> addWard(@RequestBody Ward ward)
    {
        wardService.addWard(ward);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteWard")
    public ResponseEntity<?> deleteWard(@RequestBody Ward ward)
    {
        final boolean deleted = wardService.deleteWard(ward);
        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PutMapping("/editWard/{id}")
    public ResponseEntity<?> editWard(@PathVariable(name = "id") int id, @RequestBody Ward ward)
    {
        final boolean updated = wardService.editWard(id, ward);
        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
