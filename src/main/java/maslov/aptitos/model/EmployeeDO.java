package maslov.aptitos.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import maslov.aptitos.domain.Division;
import maslov.aptitos.domain.Telephone;
import org.springframework.stereotype.Service;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Service
@Builder
public class EmployeeDO {

    private String name;
    private Telephone telephone;
    private Division division;
}
