package com.example.controllers;

import com.example.persistence.jpa.dao.ActorDao;
import com.example.persistence.jpa.entity.Actor;
import com.example.services.DateTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.Callable;

@RestController
@RequestMapping("/api")
public class ActorController {

    @Autowired
    private ActorDao actorDao;

    @Autowired
    private DateTimeService timeService;

    // The @ResponseBody annotation tells Spring MVC not to render a model into a view,
    // but rather to write the returned object into the response body. It does this by
    // using one of Spring’s message converters. This is helpful to turn objects into JSON output.

    @RequestMapping(value="/actor", method= RequestMethod.GET)
    public @ResponseBody
    Iterable<Actor> getActors() {
        return actorDao.findAll();
    }

    @RequestMapping(value="/actor/{page}/{size}", method= RequestMethod.GET)
    public @ResponseBody Iterable<Actor> getActors(@PathVariable Short page, @PathVariable Short size) {
        return actorDao.findAll(new PageRequest(page, size));
        // http://docs.spring.io/spring-data/data-commons/docs/1.6.1.RELEASE/reference/html/repositories.html
    }

    @RequestMapping(value="/actor/{id}", method= RequestMethod.GET)
    public @ResponseBody
    Callable<Actor> getActorByIdAsync(@PathVariable Integer id) {
        return () -> actorDao.findOne(id);
    }

    @RequestMapping(value="/actor", method=RequestMethod.PUT)
    public @ResponseBody Actor updateActorAsync(@RequestBody Actor Actor) {
        Actor.setLastUpdate(timeService.getCurrentTimestamp());
        return actorDao.save(Actor);
    }

    @RequestMapping(value="/actor", method=RequestMethod.POST)
    public @ResponseBody Actor createActorAsync(@RequestBody Actor Actor) {
        Actor.setLastUpdate(timeService.getCurrentTimestamp());
        return actorDao.save(Actor);
    }

    @RequestMapping(value="/actor/{id}", method= RequestMethod.DELETE)
    public @ResponseBody Callable<Integer> deleteActorByIdAsync(@PathVariable Integer id) {
        return () -> actorDao.deleteByActorId(id);
    }
}
