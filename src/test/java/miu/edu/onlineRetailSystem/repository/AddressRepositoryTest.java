package miu.edu.onlineRetailSystem.repository;

import miu.edu.onlineRetailSystem.domain.Address;
import miu.edu.onlineRetailSystem.domain.AddressType;
import miu.edu.onlineRetailSystem.domain.Customer;
import org.junit.jupiter.api.Assertions;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;


import java.util.List;


@DataJpaTest
public class AddressRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AddressRepository addressRepository;

    @Test
    public void testGetShippingAddressByCustomerId() {
        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("johndoe@example.com");
        entityManager.persist(customer);

        AddressType shippingAddressType = new AddressType("Shipping");
        entityManager.persist(shippingAddressType);

        Address shippingAddress1 = new Address();
        shippingAddress1.setStreet("123 Main St");
        shippingAddress1.setCity("City 1");
        shippingAddress1.setState("State 1");
        shippingAddress1.setZipCode("12345");
        shippingAddress1.setAddressType(shippingAddressType);
        shippingAddress1.setCustomer(customer);
        entityManager.persist(shippingAddress1);

        Address shippingAddress2 = new Address();
        shippingAddress2.setStreet("456 Elm St");
        shippingAddress2.setCity("City 2");
        shippingAddress2.setState("State 2");
        shippingAddress2.setZipCode("67890");
        shippingAddress2.setAddressType(shippingAddressType);
        shippingAddress2.setCustomer(customer);
        entityManager.persist(shippingAddress2);


        List<Address> shippingAddresses = addressRepository.getShippingAddressByCustomerId(customer.getId(), "Shipping");

        Assertions.assertNotNull(shippingAddresses);
        Assertions.assertEquals(2, shippingAddresses.size());
        Assertions.assertTrue(shippingAddresses.contains(shippingAddress1));
        Assertions.assertTrue(shippingAddresses.contains(shippingAddress2));
    }

    @Test
    public void testGetBillingAddressByCustomerId() {
        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("johndoe@example.com");
        entityManager.persist(customer);

        AddressType billingAddressType = new AddressType("Billing");
        entityManager.persist(billingAddressType);

        Address billingAddress = new Address();
        billingAddress.setStreet("123 Main St");
        billingAddress.setCity("City");
        billingAddress.setState("State");
        billingAddress.setZipCode("12345");
        billingAddress.setAddressType(billingAddressType);
        billingAddress.setCustomer(customer);
        entityManager.persist(billingAddress);

        Address retrievedBillingAddress = addressRepository.getBillingAddressByCustomerId(customer.getId(), "Billing");

        Assertions.assertNotNull(retrievedBillingAddress);
        Assertions.assertEquals(billingAddress, retrievedBillingAddress);
    }
}
