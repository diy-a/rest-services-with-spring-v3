package csci318.demo.controller;

import csci318.demo.controller.dto.LibraryDTO;
import csci318.demo.model.Library;
import csci318.demo.service.LibraryService;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class LibraryController {

    private final LibraryService libraryService;

    LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping("/libraries")
    List<LibraryDTO> all() {
        return libraryService.getAllLibraries()
                .stream()
                .map(library -> {
                    LibraryDTO libDTO = new LibraryDTO();
                    libDTO.setLibraryName(library.getName());
                    libDTO.setLocation(library.getAddress().getLocation());
                    return libDTO;
                }).collect(Collectors.toList());
    }

    @GetMapping("/libraries/{id}")
    LibraryDTO findLibraryById(@PathVariable Long id) {
        Library lib = libraryService.getLibrary(id);
        LibraryDTO libraryDTO = new LibraryDTO();
        libraryDTO.setLibraryName(lib.getName());
        libraryDTO.setLocation(lib.getAddress().getLocation());
        return libraryDTO;
    }

    @PostMapping("/libraries")
    LibraryDTO createLibrary(@RequestBody Library newLibrary) {
        Library lib = libraryService.createLibrary(newLibrary);
        LibraryDTO libraryDTO = new LibraryDTO();
        libraryDTO.setLibraryName(lib.getName());
        libraryDTO.setLocation(lib.getAddress().getLocation());
        return libraryDTO;

    }

    @PutMapping("/libraries/{id}")
    LibraryDTO updateLibraryName(@PathVariable Long id, @RequestBody Library library) {
        Library lib = libraryService.updateLibraryName(id, library);
        LibraryDTO libraryDTO = new LibraryDTO();
        libraryDTO.setLibraryName(lib.getName());
        libraryDTO.setLocation(lib.getAddress().getLocation());
        return libraryDTO;
    }

    @PutMapping("/libraries/{id}/address/{addressId}")
    LibraryDTO updateLibraryAddress(@PathVariable Long id, @PathVariable Long addressId) {
        Library lib = libraryService.updateLibraryAddress(id, addressId);
        LibraryDTO libraryDTO = new LibraryDTO();
        libraryDTO.setLibraryName(lib.getName());
        libraryDTO.setLocation(lib.getAddress().getLocation());
        return libraryDTO;
    }
}
