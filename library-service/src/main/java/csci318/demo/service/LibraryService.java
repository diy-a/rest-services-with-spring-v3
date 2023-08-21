package csci318.demo.service;

import java.util.List;

import org.springframework.web.client.RestTemplate;

import csci318.demo.model.Address;
import csci318.demo.model.Library;
import csci318.demo.repository.AddressRepository;
import csci318.demo.repository.LibraryRepository;

public class LibraryService {

    private final LibraryRepository libraryRepository;
    private final AddressRepository addressRepository;
    private final RestTemplate restTemplate;

    LibraryService(LibraryRepository libraryRepository, RestTemplate restTemplate,
            AddressRepository addressRepository) {
        this.libraryRepository = libraryRepository;
        this.restTemplate = restTemplate;
        this.addressRepository = addressRepository;
    }

    public List<Library> getAllLibraries() {
        return libraryRepository.findAll();
    }

    public Library getLibrary(Long id) {
        return libraryRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public Library createLibrary(Library newLibrary) {
        return libraryRepository.save(newLibrary);
    }

    public Library updateLibraryName(Long id, Library library) {
        Library updatedLibrary = libraryRepository.findById(id).orElseThrow(RuntimeException::new);
        updatedLibrary.setName(library.getName());
        return libraryRepository.save(updatedLibrary);
    }

    public Library updateLibraryAddress(Long id, Long addressId) {
        Library library = libraryRepository.findById(id).orElseThrow(RuntimeException::new);
        Address address = addressRepository.findById(addressId).orElseThrow(RuntimeException::new);
        library.setAddress(address);
        return libraryRepository.save(library);
    }
}
