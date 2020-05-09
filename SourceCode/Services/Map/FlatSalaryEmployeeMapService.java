package Services.Map;

import EmployeeTypes.Models.FlatSalaryEmployee;
import Services.FlatSalaryEmployeeService;

import java.util.Set;

public class FlatSalaryEmployeeMapService
        extends AbstractMapService<FlatSalaryEmployee,Double>
        implements FlatSalaryEmployeeService {

    @Override
    public FlatSalaryEmployee findById(Double id) {
        return super.findById(id);
    }

    @Override
    public FlatSalaryEmployee save(FlatSalaryEmployee object) {
        return super.save(object);
    }

    @Override
    public Set<FlatSalaryEmployee> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteBYId(Double id) {
        super.deleteById(id);
    }

    @Override
    public void delete(FlatSalaryEmployee object) {
        super.delete(object);
    }
}
