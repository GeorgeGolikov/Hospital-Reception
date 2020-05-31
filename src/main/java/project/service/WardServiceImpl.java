package project.service;

import project.entity.Diagnosis;
import project.entity.Ward;
import project.repository.WardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class WardServiceImpl implements WardService
{
    @Autowired
    private WardRepository wardRepository;

    @Override
    public List<Ward> listOfWards()
    {
        List<Ward> wardsList = (List<Ward>) wardRepository.findAll();
        wardsList.forEach(w -> w.setPeople(null));
        return wardsList;
    }

    @Override
    public Ward findWard(int id)
    {
        if (wardRepository.existsById(id))
        {
            return wardRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public boolean addWard(Ward ward)
    {
        List<Ward> allWards = (List<Ward>) wardRepository.findAll();
        List<String> allWardsNames = new ArrayList<>();
        allWards.forEach(w -> allWardsNames.add(w.getName()));
        int index = allWardsNames.indexOf(ward.getName());

        if (index == -1)
        {
            wardRepository.save(ward);
            return true;
        }
        else return false;
    }

    @Override
    public boolean deleteWard(Integer id)
    {
        if (wardRepository.existsById(id))
        {
            wardRepository.delete(wardRepository.findById(id).get());
            return true;
        }
        else return false;
    }

    @Override
    public boolean editWard(Integer id, Ward newWard)
    {
        if (wardRepository.existsById(id))
        {
            newWard.setId(id);
            wardRepository.save(newWard);
            return true;
        }
        else return false;
    }
}
