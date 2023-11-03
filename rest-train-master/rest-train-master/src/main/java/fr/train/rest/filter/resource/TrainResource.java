package fr.train.rest.filter.resource;

import fr.train.rest.filter.repository.TrainRepository;
import org.restlet.data.Status;
import org.restlet.ext.gson.GsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import java.util.UUID;

public class TrainResource extends ServerResource {

    private TrainRepository trainRepository;

    public TrainResource() {
        this.trainRepository = new TrainRepository();
    }

    @Get("json")
    public Representation get() {
        String id = getAttribute("id");
        var train = trainRepository.byId(UUID.fromString(id));
        if (train.isEmpty()) {
            setStatus(Status.CLIENT_ERROR_NOT_FOUND);
            throw new ResourceException(Status.CLIENT_ERROR_NOT_FOUND, "Train not found");
        }

        return new GsonRepresentation<>(train.get());
    }

}
