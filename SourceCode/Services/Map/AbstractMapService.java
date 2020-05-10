package Services.Map;

import EmployeeTypes.Models.Employee;

import java.util.*;

public class AbstractMapService<T extends Employee, ID extends Double> {
    private Map<Double , T> map = new HashMap<>();

    T findById(ID id){
        return map.get(id);
    }

    T save(T object){
        if(object.getId() == null)
            object.setId(getNextId(object));
        map.put(object.getId() , object);
        return object;
    }

    Set<T> findAll(){
        return new HashSet<>(map.values());
    }

    void deleteById(ID id){
        map.remove(id);
    }

    void delete(T object){
        map.entrySet().removeIf(entry -> entry.getValue().equals(object));
    }

    Double getNextId(T object){
        Double next_id = object.getLastId() + 1;
        return next_id;

    }
}
