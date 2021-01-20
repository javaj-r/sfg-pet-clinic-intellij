package com.javid.springframework.sfgpetclinicintellij.services.map;

import com.javid.springframework.sfgpetclinicintellij.model.Owner;
import com.javid.springframework.sfgpetclinicintellij.services.OwnerService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class OwnerServiceMap extends AbstractServiceMap<Owner, Long> implements OwnerService {

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Owner entity) {
        super.delete(entity);
    }

    @Override
    public Owner save(Owner entity) {
        return super.save(entity);
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Set<Owner> findByLastName(String lastName) {
        return null;
    }
}
