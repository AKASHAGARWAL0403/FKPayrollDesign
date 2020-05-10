package Services.Map;

import EmployeeTypes.Models.Employee;
import Services.EmployeeService;
import java.util.Set;


public class EmployeeMapService extends AbstractMapService<Employee,Double> implements EmployeeService {
    @Override
    public Employee findById(Double id) {
        return super.findById(id);
    }

    @Override
    public Employee save(Employee object) {
        return super.save(object);
    }

    @Override
    public Set<Employee> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteBYId(Double id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Employee object) {
        super.delete(object);
    }
}
