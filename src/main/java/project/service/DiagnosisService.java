package project.service;

import project.entity.Diagnosis;
import java.util.List;

public interface DiagnosisService
{
    List<Diagnosis> listOfDiagnosis();
    Diagnosis findDiagnosis(int id);
    boolean addDiagnosis(Diagnosis diagnosis);
    boolean deleteDiagnosis(Integer id);
    boolean editDiagnosis(Integer id, Diagnosis newDiagnosis);
}
