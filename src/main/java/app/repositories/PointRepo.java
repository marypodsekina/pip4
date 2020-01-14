package app.repositories;

import app.entities.Point;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PointRepo extends CrudRepository<Point, Integer> {
    List<Point> findAllByOwner(String owner);
    void deletePointById(Integer id);
}
