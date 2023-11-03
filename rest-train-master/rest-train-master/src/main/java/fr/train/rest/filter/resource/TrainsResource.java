package fr.train.rest.filter.resource;

import fr.train.rest.filter.model.Train;
import fr.train.rest.filter.repository.TrainRepository;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import java.util.List;

public class TrainsResource extends ServerResource {

    private TrainRepository trainRepository;

    public TrainsResource() {
        this.trainRepository = new TrainRepository();
    }

    @Get("json")
    public List<Train> list() {
        return trainRepository.all();
    }
}
