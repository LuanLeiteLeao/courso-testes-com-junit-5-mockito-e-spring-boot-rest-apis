package br.com.dicasdeumdev.api.services.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.dicasdeumdev.api.domain.User;
import br.com.dicasdeumdev.api.domain.dto.UserDTO;
import br.com.dicasdeumdev.api.repositories.UserRepository;
import br.com.dicasdeumdev.api.services.UserService;
import br.com.dicasdeumdev.api.services.exceptions.DataIntegrityViolationException;
import br.com.dicasdeumdev.api.services.exceptions.ObjectNotFoundException;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository repository; 

    @Autowired
    private ModelMapper mapper;

    @Override
    public User findById(Integer id) {
        Optional<User> obj = repository.findById(id);
        return obj.orElseThrow(()->new ObjectNotFoundException("Objeto não encontrado"));
    }

    public List<User> findAll(){
        return repository.findAll();
    }

    @Override
    @Transactional
    public User create(UserDTO obj) {
        findByEmailOrDataIntegrityViolationException(obj);
        return repository.save(mapper.map(obj,User.class));
    }
    
    @Override
    @Transactional
    public User update(UserDTO obj) {
        return this.create(obj);
        // return repository.save(mapper.map(obj,User.class));
    }

    @Override
    public void delete(Integer id) {
        findById(id);
      repository.deleteById(id);
        
    }

    private void findByEmailOrDataIntegrityViolationException(UserDTO obj){
        Optional<User> user = repository.findByEmail(obj.getEmail());
        
        if(user.isPresent() && !user.get().getId().equals(obj.getId())){
            throw new DataIntegrityViolationException("E-mail já cadastado no sistema");
        }
    }


    
}
