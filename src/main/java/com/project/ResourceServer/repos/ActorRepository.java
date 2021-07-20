package com.project.ResourceServer.repos;

        import com.project.ResourceServer.entity.Actor;
        import com.project.ResourceServer.entity.Film;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.stereotype.Repository;

        import java.util.List;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {

    Actor findActorByName(String name);
    //List<Actor> findActorsByFilm(Film film);
}
