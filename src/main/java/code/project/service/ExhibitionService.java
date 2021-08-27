package code.project.service;

import code.project.model.Exhibition;
import code.project.repository.ExhibitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ExhibitionService {

    LocalDate dateOfExhibition;    // KOSTIL
    ExhibitionRepository exhibitionRepository;


    @Autowired
    public ExhibitionService(
            ExhibitionRepository exhibitionRepository) {
        this.exhibitionRepository = exhibitionRepository;
    }

    public Exhibition show(String theme) {
        return  exhibitionRepository.findByThemeOfExhibition(theme);
    }

    public Exhibition saveExhibition (Exhibition exhibition){
        return exhibitionRepository.save(exhibition);
    }

    public List<Exhibition> index() { return exhibitionRepository.findAll();}

    public void update (String theme, Exhibition updatedExhibition) {
        Exhibition exhibitionToBeUpdated = show(theme);
        exhibitionToBeUpdated.setThemeOfExhibition((updatedExhibition.getThemeOfExhibition()));
        exhibitionToBeUpdated.setStageNumber((updatedExhibition.getStageNumber()));
        exhibitionToBeUpdated.setDateOfExhibition((updatedExhibition.getDateOfExhibition()));
        exhibitionToBeUpdated.setTicketPrice((updatedExhibition.getTicketPrice()));
    }

    public void delete (String theme) {
        Exhibition toDelete = show(theme);
        exhibitionRepository.delete(toDelete);
    }
}
