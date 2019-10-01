package maslov.aptitos.controller;

import maslov.aptitos.domain.Telephones;
import maslov.aptitos.repo.TelephonesRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<Telephones> getAll(){
        return telephonesRepo.findAll();
    }

    @GetMapping("{id}")
    public Telephones getOneTelefone( @PathVariable("id") Telephones telephones ){
        return telephones;
    }

    @PostMapping
    public Telephones createtel( @RequestBody Telephones telephones ){
        telephones.setId((long) counter++);
        return telephonesRepo.save(telephones);
    }

    @PutMapping("{id}")
    public Telephones changeTel( @PathVariable("id") Telephones telephonesFromDB,
                                 @RequestBody Telephones telephones ) {
        BeanUtils.copyProperties(telephones, telephonesFromDB, "id");
        return telephonesRepo.save(telephonesFromDB);
    }

    @DeleteMapping("{id}")
    public void delTel( @PathVariable("id") Telephones telephones ) {
        telephonesRepo.delete(telephones);
    }
}