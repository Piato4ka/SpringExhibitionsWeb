package code.project.dao;

import code.project.model.Exhibition;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class ExhibitionDAO {
    private List<Exhibition> exhibitions;
    LocalDate dateOfExhibition;

    {
        exhibitions = new ArrayList<>();

        exhibitions.add(new Exhibition("Leonardo da Vinci", 20.00, 1, (dateOfExhibition = LocalDate.of(2021, 6, 1))));
        exhibitions.add(new Exhibition("Michelangelo", 30.00, 2, (dateOfExhibition = LocalDate.of(2021, 7, 12))));
        exhibitions.add(new Exhibition("Donatello", 40.00, 3, (dateOfExhibition = LocalDate.of(2021, 6, 23))));

    }

    public List<Exhibition> index() { return exhibitions; }


    public Exhibition show(String theme) {
        return exhibitions.stream().filter(exhibition -> exhibition.getThemeOfExhibition().equals(theme)).findAny().orElse(null);
    }


    public void save(Exhibition exhibition){
        exhibitions.add(exhibition);
    }

    public void update (String theme, Exhibition updatedExhibition) {
        Exhibition exhibitionToBeUpdated = show(theme);
        exhibitionToBeUpdated.setThemeOfExhibition((updatedExhibition.getThemeOfExhibition()));
        exhibitionToBeUpdated.setStageNumber((updatedExhibition.getStageNumber()));
        exhibitionToBeUpdated.setDateOfExhibition((updatedExhibition.getDateOfExhibition()));
        exhibitionToBeUpdated.setTicketPrice((updatedExhibition.getTicketPrice()));
    }

    public void delete (String theme) {
        Exhibition toDelete = show(theme);
        exhibitions.remove(toDelete);
    }
}

