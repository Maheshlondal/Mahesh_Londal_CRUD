package Application.com.customer;




import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Embedded
    private Details details;

    @Column(name = "account_type")
    private String accountType;

    @ElementCollection
    @CollectionTable(name = "business_requirements", joinColumns = @jakarta.persistence.JoinColumn(name = "customer_id"))
    @Column(name = "requirement")
    private List<String> businessRequirements;

    @Column(name = "contract_type")
    private String contractType;

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(Long id, String name, Details details, String accountType, List<String> businessRequirements,
			String contractType) {
		super();
		this.id = id;
		this.name = name;
		this.details = details;
		this.accountType = accountType;
		this.businessRequirements = businessRequirements;
		this.contractType = contractType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Details getDetails() {
		return details;
	}

	public void setDetails(Details details) {
		this.details = details;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public List<String> getBusinessRequirements() {
		return businessRequirements;
	}

	public void setBusinessRequirements(List<String> businessRequirements) {
		this.businessRequirements = businessRequirements;
	}

	public String getContractType() {
		return contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", details=" + details + ", accountType=" + accountType
				+ ", businessRequirements=" + businessRequirements + ", contractType=" + contractType + "]";
	}

    // Getters and setters

    
}
