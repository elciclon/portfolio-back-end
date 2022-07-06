package ar.com.portfoliobackend.api.repository;

import ar.com.portfoliobackend.api.model.Language;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILanguageRepository extends JpaRepository<Language, Long>{
    List<Language> findByPersonId(Long personId);
}
