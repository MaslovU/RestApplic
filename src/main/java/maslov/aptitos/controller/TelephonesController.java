package maslov.aptitos.controller;

import maslov.aptitos.domain.Telephones;
import maslov.aptitos.repo.TelephonesRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("telephone")
public class TelephonesController {
    private int counter = 1;
    private final TelephonesRepo telephonesRepo;

    @Autowired
    public TelephonesController(TelephonesRepo telephonesRepo) {
        this.telephonesRepo = telephonesRepo;
    }

    @GetMapping
    public List<Telephones> getAll() {
        return telephonesRepo.findAll();
    }

    @GetMapping("{id}")
    public Optional<Telephones> getOneTelephone(@PathVariable Long id){
        return telephonesRepo.findById(id);
    }

    @PostMapping
    public Telephones createTel(@RequestBody Telephones telephones){
        telephones.setId((long) counter++);
        return telephonesRepo.save(telephones);
    }

    @PutMapping("{id}")
    public Telephones changeTel(
            @PathVariable("id") Telephones telephonesFromDB,
            @RequestBody Telephones telephones){
        BeanUtils.copyProperties(telephones, telephonesFromDB, "id");
        return telephonesRepo.save(telephonesFromDB);
    }

    @DeleteMapping("{id}")
    public void delTel(@PathVariable Long id) {
        telephonesRepo.deleteById(id);
    }
}