package project.service;

import project.entity.Diagnosis;
import project.repository.DiagnosisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class DiagnosisServiceImpl implements DiagnosisService
{
    @Autowired
    private DiagnosisRepository diagnosisRepository;

    @Override
    public List<Diagnosis> listOfDiagnosis()
    {
        return (List<Diagnosis>) diagnosisRepository.findAll();
    }

    @Override
    public Diagnosis findDiagnosis(int id)
    {
        if (diagnosisRepository.existsById(id))
        {
            return diagnosisRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public void addDiagnosis(Diagnosis diagnosis)
    {
        diagnosisRepository.save(diagnosis);
    }

    @Override
    public boolean deleteDiagnosis(Integer id)
    {
        if (diagnosisRepository.existsById(id))
        {
            diagnosisRepository.delete(diagnosisRepository.findById(id).get());
            return true;
        }
        else return false;
    }

    @Override
    public boolean editDiagnosis(Integer id, Diagnosis newDiagnosis)
    {
        if (diagnosisRepository.existsById(id))
        {
            newDiagnosis.setId(id);
            diagnosisRepository.save(newDiagnosis);
            return true;
        }
        else return false;
    }
}
