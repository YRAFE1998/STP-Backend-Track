package application.model;

import application.model.entities.Rating;
import application.model.entities.RatingKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, RatingKey> {


}
