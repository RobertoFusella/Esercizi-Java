package org.esempio.Settimana10;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {

    public Contact findByName(String name);

}
