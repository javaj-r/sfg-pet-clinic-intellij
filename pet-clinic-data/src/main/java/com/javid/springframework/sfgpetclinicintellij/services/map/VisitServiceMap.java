package com.javid.springframework.sfgpetclinicintellij.services.map;

import com.javid.springframework.sfgpetclinicintellij.model.Visit;
import com.javid.springframework.sfgpetclinicintellij.services.VisitService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default", "map"})
public class VisitServiceMap extends AbstractServiceMap<Visit, Long> implements VisitService {
    @Override
    public Set<Visit> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Visit save(Visit entity) {
        if (entity == null || entity.getPet() == null || entity.getPet().getId() == null
                || entity.getPet().getOwner() == null || entity.getPet().getOwner().getId() == null) {
            throw new NullEntityException("Invalid Visit entity");
        }

        return super.save(entity);
    }

    @Override
    public void delete(Visit entity) {
        super.delete(entity);
    }

    @Override
    public Visit findById(Long id) {
        return super.findById(id);
    }
}
