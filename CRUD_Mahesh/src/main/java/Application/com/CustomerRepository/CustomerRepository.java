package Application.com.CustomerRepository;


import org.springframework.data.jpa.repository.JpaRepository;

import Application.com.customer.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
}
