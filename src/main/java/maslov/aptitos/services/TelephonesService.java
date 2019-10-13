package maslov.aptitos.services;

import maslov.aptitos.domain.Telephones;
import maslov.aptitos.repo.TelephonesRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class TelephonesService {
    private int counter = 1;

    private final TelephonesRepo telephonesRepo;

    @Autowired
    public TelephonesService(TelephonesRepo telephonesRepo) {
        this.telephonesRepo = telephonesRepo;
    }

    public List<Telephones> findAllTelephones() {
        return telephonesRepo.findAll();
    }

    public Optional getOneTelephone(Long id) {
        return telephonesRepo.findById(id);
    }

    public Telephones createTelephone(Telephones telephones) {
        telephones.setId((long) counter++);
        return telephonesRepo.save(telephones);
    }

    public Telephones changeTelephones(Telephones telephones, Telephones telephonesFromDB) {
        BeanUtils.copyProperties(telephones, telephonesFromDB, "id");
        return telephonesRepo.save(telephonesFromDB);
    }

    public void deleteTelephone(Long id) {
        telephonesRepo.deleteById(id);
    }
}