package br.com.finco.finco_api.user.repositories;

import br.com.finco.finco_api.user.entities.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    
    User findByEmail(String email);

    @Query("SELECT u FROM User u INNER JOIN u.profile p WHERE u.email  LIKE %:search% OR p.name LIKE %:search%")
    Page<User> findAllWithPagination(Pageable pageable, String search);
}
