package maslov.aptitos.services;

import maslov.aptitos.domain.Telephones;
import maslov.aptitos.repo.TelephonesRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TelephonesService {

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

    public synchronized Telephones createTelephone(Telephones telephones) {
        return telephonesRepo.save(telephones);
    }

    @Transactional
    public Telephones changeTelephones(Telephones telephones, Telephones telephonesFromDB) {
        BeanUtils.copyProperties(telephones, telephonesFromDB, "id");
        return telephonesRepo.save(telephonesFromDB);
    }

    @Transactional
    public void deleteTelephone(Long id) {
        telephonesRepo.deleteById(id);
    }
}