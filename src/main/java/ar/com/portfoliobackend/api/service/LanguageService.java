package ar.com.portfoliobackend.api.service;

import ar.com.portfoliobackend.api.model.Language;
import ar.com.portfoliobackend.api.repository.ILanguageRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LanguageService {
    @Autowired
    ILanguageRepository languageRepository;
    public Language findById;
    public List<Language> findByPersonId(Long personId) {
        return languageRepository.findByPersonId(personId);
    }
    
    public Language saveLanguage(Language language){
        return languageRepository.save(language);
    }

    public Optional<Language> findById(Long languageId) {
        return languageRepository.findById(languageId);
    }

    public void deleteLanguageById(Long languageId) {
        languageRepository.deleteById(languageId);
    }
    
}
