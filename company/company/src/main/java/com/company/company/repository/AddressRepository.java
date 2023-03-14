package com.company.company.repository;
import com.company.company.entity.Address;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    boolean existsByHomeNumber(int homeNumber);

    boolean existsById(Long id);


    boolean existsByHomeNumberAndIdNot(Long id, int homeNumber);

}
