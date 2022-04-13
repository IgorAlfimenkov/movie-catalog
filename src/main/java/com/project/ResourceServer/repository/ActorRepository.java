package com.project.ResourceServer.repository;

        import com.project.ResourceServer.entity.Actor;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {

    Actor findActorByName(String name);
    //List<Actor> findActorsByFilm(Film film);
}
