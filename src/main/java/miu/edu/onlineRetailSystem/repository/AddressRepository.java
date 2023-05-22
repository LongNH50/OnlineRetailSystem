package miu.edu.onlineRetailSystem.repository;

import miu.edu.onlineRetailSystem.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
    @Query("SELECT a FROM Address a WHERE a.customer.id = :customerId AND a.addressType.name = :shippingAddress")
    List<Address> getShippingAddressByCustomerId(@Param("customerId") Integer customerId, @Param("shippingAddress") String shippingAddress);

    @Query("SELECT a FROM Address a WHERE a.customer.id = :customerId AND a.addressType.name = :billingAddress")
    Address getBillingAddressByCustomerId(@Param("customerId") Integer customerId, @Param("billingAddress") String billingAddress);

}
