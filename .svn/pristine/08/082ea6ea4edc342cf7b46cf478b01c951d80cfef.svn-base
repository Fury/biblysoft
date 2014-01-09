package org.bibly.logic.dao.interfaces;

import org.bibly.logic.models.Vergaderruimte;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface IVergaderruimteDAO
{
    public Vergaderruimte findById(long id);

    public List<Vergaderruimte> findByPrijs(double prijs);

    public List<Vergaderruimte> findByExample(Vergaderruimte ruimte);

    public Vergaderruimte save(Vergaderruimte ruimte);

    public List<Vergaderruimte> save(List<Vergaderruimte> ruimte);

    public List<Vergaderruimte> findAll();

    public List<Vergaderruimte> findByMaxPersonen(int max);

    void delete(Vergaderruimte vergaderruimte);
}
