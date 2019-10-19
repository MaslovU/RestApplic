package maslov.aptitos.services;

import maslov.aptitos.domain.Divisions;
import maslov.aptitos.repo.DivisionsRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DivisionsService {

    private final DivisionsRepo divisionsRepo;

    @Autowired
    public DivisionsService(DivisionsRepo divisionsRepo) {
        this.divisionsRepo = divisionsRepo;
    }
    
    public List<Divisions> allDivisions() {
        return divisionsRepo.findAll();
    }

    public Optional<Divisions> oneDivision(Long id) {
        return divisionsRepo.findById(id);
    }

    @Transactional
    public synchronized Divisions createNewDivision(Divisions divisions) {
        return divisionsRepo.save(divisions);
    }

    @Transactional
    public Divisions updateDivision(Divisions divisions, Divisions divisionsFromDB) {
        BeanUtils.copyProperties(divisions, divisionsFromDB, "id");
        return divisionsRepo.save(divisionsFromDB);
    }

    @Transactional
    public void deleteDivision(Long id) {
        divisionsRepo.deleteById(id);
    }
}