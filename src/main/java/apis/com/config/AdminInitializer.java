package apis.com.config; 
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import apis.com.entities.User;
import apis.com.enums.Role;
import apis.com.repository.UserRepository;

@Component
public class AdminInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${admin.email:admin@example.com}")  // Par défaut, "admin" si non configuré
    private String adminEmail;

    @Value("${admin.password:admin123}")  // Par défaut, "admin123" si non configuré
    private String adminPassword;

    public AdminInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        // Vérifier si un utilisateur ADMIN existe
        if (userRepository.findByEmail(adminEmail).isEmpty()) {
            // Créer un nouvel utilisateur avec le rôle ADMIN
            User admin = User.builder()
                    .fullName("Rami Ghaouar") 
                    .email(adminEmail)
                    .role(Role.ADMIN)
                    .password(passwordEncoder.encode(adminPassword))
                    .build(); 

            userRepository.save(admin);
            System.out.println("Administrateur créé avec succès : " + adminEmail);
        } else {
            System.out.println("Administrateur déjà existant.");
        }
    }
}