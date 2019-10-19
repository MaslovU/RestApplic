package maslov.aptitos.services;

import maslov.aptitos.domain.Divisions;
import maslov.aptitos.repo.DivisionsRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DivisionsService {
    private int counter = 1;
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

    public Divisions createNewDivision(Divisions divisions) {
        divisions.setId((long) counter++);
        return divisionsRepo.save(divisions);
    }

    public Divisions updateDivision(Divisions divisions, Divisions divisionsFromDB) {
        BeanUtils.copyProperties(divisions, divisionsFromDB, "id");
        return divisionsRepo.save(divisionsFromDB);
    }

    public void deleteDivision(Long id) {
        divisionsRepo.deleteById(id);
    }
}