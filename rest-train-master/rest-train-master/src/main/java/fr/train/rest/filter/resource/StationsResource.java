package fr.train.rest.filter.resource;
// Déclaration du package auquel appartient la classe StationsResource

import fr.train.rest.filter.repository.TrainRepository;
// Import de la classe TrainRepository du package fr.train.rest.filter.repository

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
// Import des classes nécessaires du framework Restlet pour gérer les ressources REST

import java.util.List;
import java.util.stream.Stream;
// Import des classes List et Stream du package java.util pour manipuler les données

public class StationsResource extends ServerResource {
    // Déclaration de la classe StationsResource, qui étend ServerResource, indiquant qu'elle est une ressource REST

    private TrainRepository trainRepository;
    // Déclaration d'une variable trainRepository de type TrainRepository pour accéder aux données

    public StationsResource() {
        this.trainRepository = new TrainRepository();
        // Initialisation de l'objet trainRepository lors de la création d'une instance de la classe
    }

    @Get("json")
    // Annotation indiquant que la méthode suivante est associée à la méthode HTTP GET et renvoie des données au format JSON
    public List<String> list() {
        // Déclaration de la méthode list qui renverra une liste de chaînes de caractères

        return trainRepository.all().stream()
                // Obtention de la liste de tous les trains à partir de l'objet trainRepository, puis création d'un flux de données

                .flatMap(train -> Stream.of(train.getDeparture(), train.getArrival()))
                // Extraction des gares de départ et d'arrivée de chaque train et fusion dans un seul flux

                .distinct()
                // Suppression des doublons du flux

                .toList();
        // Conversion du flux en une liste de chaînes de caractères et renvoi de cette liste comme résultat de la méthode
    }
}
