package org.bibly.logic.dao.interfaces;

import org.bibly.logic.models.ItemExemplaar;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Dries on 11/21/13.
 */
@Transactional
public interface IExemplaarDAO
{

    public ItemExemplaar save(ItemExemplaar itemExemplaar);

    public void save(List<ItemExemplaar> itemExemplaar);

    public boolean isBeschikbaar(long itemID);

    public ItemExemplaar findById(long artikelNr);

    public void delete(ItemExemplaar exemplaar);

    List<ItemExemplaar> findAll();
}
