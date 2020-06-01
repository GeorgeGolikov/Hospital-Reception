package project.service;

import project.entity.Diagnosis;
import project.entity.Ward;
import project.repository.DiagnosisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class DiagnosisServiceImpl implements DiagnosisService
{
    @Autowired
    private DiagnosisRepository diagnosisRepository;

    @Override
    public List<Diagnosis> listOfDiagnosis()
    {
        List<Diagnosis> diagnosisList = (List<Diagnosis>) diagnosisRepository.findAll();
        diagnosisList.forEach(d -> {
            d.getPeople().forEach(p -> {
                Set<Diagnosis> diagnosisSet = p.getDiagnosis();
                diagnosisSet.forEach(di -> {
                    if (di != null) di.setPeople(null);
                });
            });
        });
        return diagnosisList;
    }

    @Override
    public Diagnosis findDiagnosis(int id)
    {
        if (diagnosisRepository.existsById(id))
        {
            Diagnosis diagnosis = diagnosisRepository.findById(id).get();
            diagnosis.getPeople().forEach(p -> {
                Set<Diagnosis> diagnosisList = p.getDiagnosis();
                diagnosisList.forEach(d -> {
                    if (d != null) d.setPeople(null);
                });
            });
            return diagnosis;
        }
        return null;
    }

    @Override
    public boolean addDiagnosis(Diagnosis diagnosis)
    {
        List<Diagnosis> allDiagnosis = (List<Diagnosis>) diagnosisRepository.findAll();
        List<String> allDiagnosisNames = new ArrayList<>();
        allDiagnosis.forEach(d -> allDiagnosisNames.add(d.getName()));
        int index = allDiagnosisNames.indexOf(diagnosis.getName());

        if (index == -1)
        {
            diagnosisRepository.save(diagnosis);
            return true;
        }
        else return false;
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
