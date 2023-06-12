package maslov.aptitos.services;

import maslov.aptitos.domain.Telephone;
import maslov.aptitos.repo.TelephonesRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TelephonesService {

    private final TelephonesRepo telephonesRepo;

    public TelephonesService(TelephonesRepo telephonesRepo) {
        this.telephonesRepo = telephonesRepo;
    }

    public List<Telephone> findAllTelephones() {
        return telephonesRepo.findAll();
    }

    public Optional<Telephone> getOneTelephone(Long id) {
        return telephonesRepo.findById(id);
    }

    @Transactional
    public Telephone createTelephone(Telephone telephones) {
        return telephonesRepo.save(telephones);
    }

    @Transactional
    public Telephone changeTelephones(Telephone telephones, Telephone telephonesFromDB) {
        BeanUtils.copyProperties(telephones, telephonesFromDB, "id");
        return telephonesRepo.save(telephonesFromDB);
    }

    @Transactional
    public void deleteTelephone(Long id) {
        telephonesRepo.deleteById(id);
    }
}