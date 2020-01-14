package app.controllers;


import app.data.ResponseMessage;
import app.entities.Point;
import app.entities.User;
import app.service.PointsService;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@EnableWebSecurity
public class PointsController {

    @Autowired
    PointsService pointsService;

    @Autowired
    UserService userService;

    @PostMapping("/add")
    public ResponseMessage addPoint(@RequestBody Point point){
        if((point.getR() == null) || (point.getX() == null) || (point.getY() == null) ||
                (point.getY().isNaN()) ||(point.getX().isNaN()) || (point.getR().isNaN()))
            return new ResponseMessage(400, "x/y/r is null");

        String username = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName()).getUsername();
        if ((username != null) && (username.isEmpty() == false)) {
            point.setOwner(username);
            pointsService.addPoint(point);
            List<Point> list = pointsService.getAllPointsByUsername(username);
            return new ResponseMessage(200, list);
        } else return new ResponseMessage(400, "No owner!");
    }

    @GetMapping("/show")
    public ResponseMessage showAllPointByUsername(){
        User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        System.out.println(user.getUsername());
        try {
            if ((user.getUsername() == null) || (user.getUsername().isEmpty()))
                return new ResponseMessage(400, null);
        } catch (NullPointerException ex){
            return new ResponseMessage(400, null);
        }
        List<Point> list = pointsService.getAllPointsByUsername(user.getUsername());
        return new ResponseMessage(200, list);
    }

    @PostMapping("/update")
    public ResponseMessage updatePoint(@RequestBody Point request){
        User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        List<Point> list = pointsService.getAllPointsByUsername(user.getUsername());
        if(request.getId() != null) {
            request.setOwner(user.getUsername());
            pointsService.updatePoint(request);

            list = pointsService.getAllPointsByUsername(user.getUsername());

            return new ResponseMessage(200, list);
        } else return new ResponseMessage(400, list);
    }

    @PostMapping("/delete")
    public ResponseMessage deletePoint(@RequestBody Point request){
        User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        List<Point> list = pointsService.getAllPointsByUsername(user.getUsername());
        if(request.getId() != null) {
            request.setOwner(user.getUsername());
            pointsService.deletePoint(request);

            list = pointsService.getAllPointsByUsername(user.getUsername());

            return new ResponseMessage(200, list);
        } else return new ResponseMessage(400, list);
    }


}
