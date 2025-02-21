package com.univ.kandan;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.univ.kandan.model.Kanban;
import com.univ.kandan.model.User;
import com.univ.kandan.repository.KanbanRepository;
import com.univ.kandan.repository.UserRepository;

@EnableAutoConfiguration
@SpringBootApplication
public class KandanApplication {

    public static void main(String[] args) {
        SpringApplication.run(KandanApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public CommandLineRunner SeedApplication(UserRepository userRepository, KanbanRepository kanbanRepository,
            PasswordEncoder passwordEncoder) {
        return (args) -> {
            loadUserData(userRepository, passwordEncoder);
            loadKanbanData(kanbanRepository, userRepository);
        };
    }

    private void loadUserData(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        if (userRepository.count() == 0) {
            User user1 = new User();
            user1.setPrenom("Thomas");
            user1.setNom("Hamelin");
            user1.setEmail("thomas.hamelin@univ-rouen.fr");
            user1.setPassword(passwordEncoder.encode("azeqsdwxc.2022"));

            User user2 = new User();
            user2.setPrenom("Lucas");
            user2.setNom("Ficker");
            user2.setEmail("lucas.ficker@univ-rouen.fr");
            user2.setPassword(passwordEncoder.encode("azeqsdwxc.2022"));

            userRepository.save(user1);
            userRepository.save(user2);
        }
    }

    private void loadKanbanData(KanbanRepository kanbanRepository, UserRepository userRepository) {
        if (kanbanRepository.count() == 0 && userRepository.count() != 0) {
            User user1 = userRepository.findByEmail("thomas.hamelin@univ-rouen.fr").orElseThrow();
            User user2 = userRepository.findByEmail("lucas.ficker@univ-rouen.fr").orElseThrow();
            Kanban kanban1 = new Kanban();
            kanban1.setNom("Projet 1");
            kanban1.setDescription("Ceci est le premier projet de l'application kanban que nous venons de réaliser");
            kanban1.setCreator(user1);
            kanban1.setIsPublic(true);

            Kanban kanban2 = new Kanban();
            kanban2.setNom("Projet 2");
            kanban2.setDescription("Ceci est le deuxième projet de l'application kanban que nous venons de réaliser");
            kanban2.setCreator(user2);
            kanban2.setIsPublic(false);

            kanban1 = kanbanRepository.save(kanban1);
            kanban2 = kanbanRepository.save(kanban2);

            kanban1.addMember(user2);
            kanban1.addMember(user1);

            kanban2.addMember(user2);
            kanban2.addMember(user1);

            user1.addKanban(kanban2);
            user1.addKanban(kanban1);

            user2.addKanban(kanban2);
            user2.addKanban(kanban1);

            kanbanRepository.save(kanban1);
            kanbanRepository.save(kanban2);
            userRepository.save(user1);
            userRepository.save(user2);
        }
    }
}
