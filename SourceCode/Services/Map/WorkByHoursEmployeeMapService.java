package Services.Map;

import EmployeeTypes.Models.WorkByHoursEmployee;
import Services.WorkByHoursEmployeeService;

import java.util.Set;

public class WorkByHoursEmployeeMapService
        extends AbstractMapService<WorkByHoursEmployee,Double>
        implements WorkByHoursEmployeeService {

    @Override
    public WorkByHoursEmployee findById(Double id) {
        return super.findById(id);
    }

    @Override
    public WorkByHoursEmployee save(WorkByHoursEmployee object) {
        return super.save(object);
    }

    @Override
    public Set<WorkByHoursEmployee> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteBYId(Double id) {
        super.deleteById(id);
    }

    @Override
    public void delete(WorkByHoursEmployee object) {
        super.delete(object);
    }
}
