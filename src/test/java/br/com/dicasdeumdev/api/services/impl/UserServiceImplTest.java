package br.com.dicasdeumdev.api.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.dicasdeumdev.api.domain.User;
import br.com.dicasdeumdev.api.domain.dto.UserDTO;
import br.com.dicasdeumdev.api.repositories.UserRepository;
import br.com.dicasdeumdev.api.services.exceptions.ObjectNotFoundException;

@SpringBootTest
public class UserServiceImplTest {

    private static final String PASSWORD = "123";
    private static final String EMAIL = "valdir@gmail.com";
    private static final String NAME = "Valdir";
    private static final Integer ID = 1;

    @InjectMocks
    // se usa o inject mocks quando se precisa injetar 
    // uma dependencia de uma instancia real do metodos, neste caso precisa-se de um service
    // real para fazer os teste
    private UserServiceImpl service;
    @Mock 
    // não precisa de uma instacial real, não precisa acessar os dados do banco de dados
    // estes sertão mokados, seram criados de mentirinha, ou seja seram inseridas os dados
    private UserRepository repository;
    @Mock 
    private ModelMapper mapper;

    private User user;
    private UserDTO userDTO;
    private Optional<User> optionalUser;


    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void testCreate() {

    }

    @Test
    void testDelete() {

    }

    @Test
    void testFindAll() {

    }

    @Test
    void whenFindByIdThenReturnAnUserInstance() {
        Mockito.when(repository.findById(Mockito.anyInt())).thenReturn(optionalUser);

        User reponse = service.findById(ID);

        Assertions.assertEquals(User.class, reponse.getClass());
        Assertions.assertNotNull(reponse);
        
        Assertions.assertEquals(ID, reponse.getId());
        Assertions.assertEquals(EMAIL, reponse.getEmail());
        Assertions.assertEquals(NAME, reponse.getName());
    }

    @Test
    void whenFindByIdThenReturnAnObjectNotFoundException() {
        Mockito.when(repository.findById(Mockito.anyInt())).thenThrow(new ObjectNotFoundException("Objeto não encontrado"));

        try{
            service.findById(ID);
        }catch(Exception ex){
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals("Objeto não encontrado", ex.getMessage());
        }
    }

    @Test
    void testUpdate() {

    }

    private void startUser(){
        user = new User(ID,NAME,EMAIL,PASSWORD);
        userDTO = new UserDTO(ID,NAME,EMAIL,PASSWORD);
        optionalUser = Optional.of(new User(ID,NAME,EMAIL,PASSWORD));
    }
}
