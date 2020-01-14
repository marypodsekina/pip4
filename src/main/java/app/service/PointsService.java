package app.service;

import app.entities.Point;
import app.repositories.PointRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PointsService {

    @Autowired
    private PointRepo pointRepo;

    public void addPoint(Point point){
        point.setChecked(checkPoint(point));

        pointRepo.save(point);
    }

    public List<Point> getAllPointsByUsername(String username) {
        return pointRepo.findAllByOwner(username);
    }

    public boolean checkPoint(Point p){
        double x = p.getX();
        double y = p.getY();
        double r = p.getR();
        return (x <= 0 && y >= 0 && y <= r && Math.abs(x) < r / 2) || (x >= 0 && y >= 0 && x * x + y * y <= r * r / 4) || (x >= 0 && y <= 0 && x <= y + r / 2);
    }

    public void updatePoint(Point point){
        pointRepo.deletePointById(point.getId());
        addPoint(point);
    }

    public void deletePoint(Point point){
        pointRepo.delete(point);
    }
}
