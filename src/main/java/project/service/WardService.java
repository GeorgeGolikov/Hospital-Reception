package project.service;

import project.entity.Ward;
import java.util.List;

public interface WardService
{
    List<Ward> listOfWards();
    Ward findWard(int id);
    void addWard(Ward ward);
    boolean deleteWard(Integer id);
    boolean editWard(Integer id, Ward newWard);
}
