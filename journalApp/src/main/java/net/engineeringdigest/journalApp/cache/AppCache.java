package net.engineeringdigest.journalApp.cache;

import jakarta.annotation.PostConstruct;
import net.engineeringdigest.journalApp.entity.ConfigJournalAppEntity;
import net.engineeringdigest.journalApp.repository.ConfigJournalAppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

    public enum keys{
        WEATHER_API;
    }

    @Autowired
    private ConfigJournalAppRepository repository;

    public Map<String,String> APP_CACHE=new HashMap<>();

    @PostConstruct
    public void init(){
        APP_CACHE=new HashMap<>();
        List<ConfigJournalAppEntity> journalAppEntities = repository.findAll();
        for(ConfigJournalAppEntity entity: journalAppEntities){
            APP_CACHE.put(entity.getKey(),entity.getValue());
        }
    }
}
