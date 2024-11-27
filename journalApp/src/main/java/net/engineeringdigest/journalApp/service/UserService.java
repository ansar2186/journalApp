package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserRepository repository;

    public void saveUser(User user) {
        repository.save(user);
    }

    public List<User> getAll(){
        return repository.findAll();
    }

    public Optional<User> findById(ObjectId id){
        return repository.findById(id);
    }

    public void deleteById(ObjectId id){
        repository.deleteById(id);

    }

    public User findByUserName(String userName){
        return repository.findByUserName(userName);
    }
}
