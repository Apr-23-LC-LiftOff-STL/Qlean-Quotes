package org.launchcode.qleanquotes.models.data;


import org.launchcode.qleanquotes.models.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Integer> {

}
