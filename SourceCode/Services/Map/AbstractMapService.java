package Services.Map;

import EmployeeTypes.Models.Employee;

import java.util.*;

public class AbstractMapService<T extends Employee, ID extends Double> {
    private Map<Double , T> map = new HashMap<>();

    T findById(ID id){
        return map.get(id);
    }

    T save(T object){
        object.setId(getNextId());
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

    Double getNextId(){
        Double next_id = null;
        try{
            next_id = Collections.max(map.keySet()) + 1;
        }catch (NoSuchElementException e){
            next_id = 1D;
        }
        return next_id;
    }
}
