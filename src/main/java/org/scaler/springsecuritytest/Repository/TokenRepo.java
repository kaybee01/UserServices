package org.scaler.springsecuritytest.Repository;

import lombok.Data;
import org.scaler.springsecuritytest.Model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;


@Repository
public interface TokenRepo extends JpaRepository<Token , Integer> {

    Optional<Token> findByValue(String token);

    Optional<Token> findByValueAndIsValid(String toke, boolean isValid);
}
