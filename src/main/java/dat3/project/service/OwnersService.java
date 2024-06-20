package dat3.project.service;

import dat3.project.dto.OwnersDtoRequest;
import dat3.project.dto.OwnersDtoResponse;
import dat3.project.entity.Owners;
import dat3.project.entity.Pets;
import dat3.project.repository.OwnersRepository;
import dat3.project.repository.PetsRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OwnersService {
    private final OwnersRepository ownersRepository;
    private final PetsRepository petsRepository;

    public OwnersService(OwnersRepository ownersRepository, PetsRepository petsRepository) {
        this.ownersRepository = ownersRepository;
        this.petsRepository = petsRepository;
    }

    /**
     * Gets all owners
     *
     * @return A list of owners
     */
    public List<OwnersDtoResponse> getAllOwners() {
        List<Owners> owners = ownersRepository.findAll();
        return owners.stream()
                .map(OwnersDtoResponse::new)
                .collect(Collectors.toList());
    }

    /**
     * Gets an owner by id
     *
     * @param id The id of the owner
     * @return The owner
     */
    public OwnersDtoResponse getOwnersById(int id) {
        Owners owners = ownersRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Owners not found"));
        return new OwnersDtoResponse(owners);
    }

    /**
     * Adds an owner
     *
     * @param request The request containing the owner data
     * @return The added owner
     */
    public OwnersDtoResponse addOwners(OwnersDtoRequest request) {
        Owners newOwners = new Owners();
        updateOwners(newOwners, request);
        ownersRepository.save(newOwners);

        // Handle pets if included in the request
        if (request.getPets() != null) {
            request.getPets().forEach(petRequest -> {
                Pets pet = new Pets(petRequest.getName(), petRequest.getSpecies(), petRequest.getColor());
                pet.setOwner(newOwners);
                petsRepository.save(pet);
            });
        }

        return new OwnersDtoResponse(newOwners);
    }

    /**
     * Edits an Owner
     *
     * @param request The request containing the data
     * @param id      The id of the Owner
     * @return The edited Owner
     */
    public OwnersDtoResponse editOwners(OwnersDtoRequest request, int id) {
        Owners owners = ownersRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Owners not found"));
        updateOwners(owners, request);
        ownersRepository.save(owners);
        return new OwnersDtoResponse(owners);
    }

    /**
     * Deletes an Owner
     * @param id The id of the Owner
     * @return A response entity
     */
    public ResponseEntity<Void> deleteOwners(int id) {
        Owners owners = ownersRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Owners not found"));
        ownersRepository.delete(owners);
        return ResponseEntity.ok().build();
    }

    /**
     * Updates an Owner with the data from a request
     * @param owners The owner to update
     * @param request The request containing the data
     */
    private void updateOwners(Owners owners, OwnersDtoRequest request) {
        owners.setName(request.getName());
        owners.setAge(request.getAge());
    }
}
