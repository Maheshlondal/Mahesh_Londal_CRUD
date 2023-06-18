package Application.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;



import Application.com.CustomerRepository.CustomerRepository;
import Application.com.customer.Customer;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        
        if (customer == null) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(customer);
    }
    
    
    
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable Long id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer == null) {
            return ResponseEntity.notFound().build();
        }

        customer.getBusinessRequirements().size(); // Eagerly fetch businessRequirements

        customerRepository.deleteById(id);

        return ResponseEntity.ok(customer);
    }
    
    
    
    
    
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer updatedCustomer) {
        Customer customer = customerRepository.findById(id).orElse(null);

        if (customer == null) {
            return ResponseEntity.notFound().build();
        }

        // Update the fields of the existing customer object with the new values
        customer.setName(updatedCustomer.getName());
        customer.setDetails(updatedCustomer.getDetails());
        customer.setAccountType(updatedCustomer.getAccountType());
        customer.setBusinessRequirements(updatedCustomer.getBusinessRequirements());
        customer.setContractType(updatedCustomer.getContractType());

        // Perform validation on the updated customer object
        if (!isCustomerValid(customer)) {
            return ResponseEntity.badRequest().build();
        }

        // Save the updated customer to the database
        Customer updated = customerRepository.save(customer);

        return ResponseEntity.ok(updated);
    }

    // Helper method to validate the customer object
    private boolean isCustomerValid(Customer customer) {
        // Validate sex
        String sex = customer.getDetails().getSex();
        if (!sex.equals("M") && !sex.equals("F")) {
            return false;
        }

        // Validate dob
        String dob = customer.getDetails().getDob();
        // Assuming the dob format is dd-MM-yyyy
        String[] dobParts = dob.split("-");
        int year = Integer.parseInt(dobParts[2]);
        if (year >= 2002) {
            return false;
        }

        // Validate contractType
        String contractType = customer.getContractType();
        if (!contractType.equals("fulltime") && !contractType.equals("parttime")) {
            return false;
        }

        return true;
    }
    
    
    
    
    
    
    
    
    @PostMapping
    public ResponseEntity<Customer> createCustomer(@Validated @RequestBody Customer customer) {
        // Perform validation here before saving to the database

        // Validate sex
        if (!customer.getDetails().getSex().equals("M") && !customer.getDetails().getSex().equals("F")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        // Validate dob
        // Assuming the dob format is dd-MM-yyyy
        if (!isDobValid(customer.getDetails().getDob())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        // Validate contractType
        if (!customer.getContractType().equals("fulltime") && !customer.getContractType().equals("parttime")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        Customer createdCustomer = customerRepository.save(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
    }

    // Helper method to validate the dob
    private boolean isDobValid(String dob) {
        // Assuming the dob format is dd-MM-yyyy
        String[] parts = dob.split("-");
        int year = Integer.parseInt(parts[2]);
        int month = Integer.parseInt(parts[1]);
        int day = Integer.parseInt(parts[0]);

        // Check if dob is before 01-01-2002
        if (year > 2002) {
            return false;
        } else if (year == 2002 && month > 1) {
            return false;
        } else if (year == 2002 && month == 1 && day >= 1) {
            return false;
        }

        return true;
    }
}
