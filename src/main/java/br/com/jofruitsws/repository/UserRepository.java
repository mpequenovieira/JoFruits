package br.com.jofruitsws.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.jofruitsws.entity.User;

public interface UserRepository  extends CrudRepository<User, Integer>{

}
