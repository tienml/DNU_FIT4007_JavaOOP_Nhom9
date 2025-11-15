package service;

import model.Lecturer;
import repository.lecturerRepository;

import java.util.List;
import java.util.Optional;

public class lecturerService {
    private final lecturerRepository repo;

    public lecturerService(lecturerRepository repo) {
        this.repo = repo;
    }

    public boolean addLecturer(Lecturer l) {
        return repo.add(l);
    }

    public boolean updateLecturer(Lecturer l) {
        return repo.update(l);
    }

    public boolean deleteLecturer(String id) {
        return repo.delete(id);
    }

    public Optional<Lecturer> findById(String id) {
        return repo.findById(id);
    }

    public List<Lecturer> getAll() {
        return repo.getAll();
    }
}
