package project.service;

import project.entity.Diagnosis;
import java.util.List;

public interface DiagnosisService
{
    List<Diagnosis> listOfDiagnosis();
    Diagnosis findDiagnosis(int id);
    void addDiagnosis(Diagnosis diagnosis);
    boolean deleteDiagnosis(Diagnosis diagnosis);
    boolean editDiagnosis(Integer id, Diagnosis newDiagnosis);
}
