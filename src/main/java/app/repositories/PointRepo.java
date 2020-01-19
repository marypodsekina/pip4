package app.repositories;

import app.entities.Point;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PointRepo extends CrudRepository<Point, Integer> {
    List<Point> findAllByOwner(String owner);
    void deletePointById(Integer id);
}
