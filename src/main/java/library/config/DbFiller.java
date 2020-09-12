package library.config;

import java.util.Set;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import library.domain.customer.Customer;
import library.domain.customer.RepositoryCustomer;
import library.domain.customer.role.RepositoryRole;
import library.domain.customer.role.Role;
import library.domain.customer.role.RoleName;

@Component
public class DbFiller implements ApplicationRunner {

  private static final String PASSWORD = "password";

  private RepositoryRole roleRepo;
  private RepositoryCustomer customerRepo;

  public DbFiller(RepositoryRole roleRepo, RepositoryCustomer customerRepo) {
    super();
    this.roleRepo = roleRepo;
    this.customerRepo = customerRepo;
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {

    this.initRoles();
    this.initCustomers();
  }


  @Transactional
  private void initRoles() {
    Role adminRole = new Role(RoleName.ADMIN.name(), "Admin of system.");
    Role customerRole = new Role(RoleName.CUSTOMER.name(), "Typical boring customer.");
    Set<Role> roles = Set.of(adminRole, customerRole);

    roleRepo.saveAll(roles);
  }

  @Transactional
  private void initCustomers() {
    Set<Role> adminRoles = roleRepo.findAllByNameIn(Role.ADMIN_ROLES);
    Customer admin = new Customer("Krzysio", "K", "admin@library.pl",
        PASSWORD, adminRoles);

    Set<Role> customerRoles = roleRepo.findAllByNameIn(Role.CUSTOMER_ROLES);
    Customer customer = new Customer("Rudy", "K", "customer@library.pl",
        PASSWORD, customerRoles);

    Set<Customer> customers = Set.of(admin, customer);
    customerRepo.saveAll(customers);
  }

  public static void main(String... strings) {
    System.out.println(BCrypt.hashpw(PASSWORD, BCrypt.gensalt(15)));
  }

}
