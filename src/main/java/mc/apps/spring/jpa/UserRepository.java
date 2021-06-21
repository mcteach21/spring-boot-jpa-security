package mc.apps.spring.jpa;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {
    List<User> findByLastname(String lastname);
    User findByLogin(String login);
    User findById(int id);
}
