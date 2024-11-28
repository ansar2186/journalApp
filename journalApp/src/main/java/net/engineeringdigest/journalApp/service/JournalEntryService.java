package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository repository;
    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntity, String userName) {
        User user = userService.findByUserName(userName);
        journalEntity.setDate(LocalDateTime.now());
        JournalEntry saved = repository.save(journalEntity);
        user.getJournalEntries().add(saved);
        userService.saveUser(user);
    }

    public void saveEntry(JournalEntry journalEntity) {
        repository.save(journalEntity);
    }

    public List<JournalEntry> getAll() {
        return repository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id) {
        return repository.findById(id);
    }

    public void deleteById(ObjectId id, String userName) {
        User user = userService.findByUserName(userName);
        user.getJournalEntries().removeIf(x->x.getId().equals(id));
        userService.saveUser(user);
        repository.deleteById(id);

    }
}
