package code.project.repository;

import code.project.model.Exhibition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExhibitionRepository extends JpaRepository <Exhibition, Integer> {
    Exhibition findByThemeOfExhibition(String themeOfExhibition);
    List <Exhibition> findAll();
    void delete (Exhibition exhibition);

}
