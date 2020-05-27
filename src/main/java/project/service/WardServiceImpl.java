package project.service;

import project.entity.Ward;
import project.repository.WardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class WardServiceImpl implements WardService
{
    @Autowired
    private WardRepository wardRepository;

    @Override
    public List<Ward> listOfWards()
    {
        return (List<Ward>) wardRepository.findAll();
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
    public void addWard(Ward ward)
    {
        wardRepository.save(ward);
    }

    @Override
    public boolean deleteWard(Ward ward)
    {
        if (wardRepository.existsById(ward.getId()))
        {
            wardRepository.delete(ward);
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
