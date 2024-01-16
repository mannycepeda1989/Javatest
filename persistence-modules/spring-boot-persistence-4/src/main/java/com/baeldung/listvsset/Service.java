package com.baeldung.listvsset;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class Service<S> extends ParametrizationAware<S> {

    private final JpaRepository<S, Long> repository;

    public Service(JpaRepository<S, Long> repository) {
        this.repository = repository;
    }

    public JpaRepository<S, Long> getRepository() {
        return repository;
    }

    public int countNumberOfRequestsWithFunction(ToIntFunction<List<S>> function) {
        return function.applyAsInt(repository.findAll());
    }


    public Optional<S> getUserById(Long id) {
        return repository.findById(id);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public List<S> saveAll(Iterable<S> entities) {
        return repository.saveAll(entities);
    }

    public List<S> findAll() {
        return repository.findAll();
    }

    public Optional<S> getUserByIdWithPredicate(long id, Predicate<S> predicate) {
        Optional<S> user = repository.findById(id);
        if (user.isPresent() && predicate.test(user.get())) {
            // pass
        }
        return user;
    }

    public int getUserByIdWithFunction(Long id, ToIntFunction<Optional<S>> function) {
        return function.applyAsInt(repository.findById(id));
    }

    public void save(S entity) {
        repository.save(entity);
    }
}
