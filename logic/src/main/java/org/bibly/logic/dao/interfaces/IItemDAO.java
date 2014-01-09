package org.bibly.logic.dao.interfaces;

import org.bibly.logic.models.Item;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Dries on 11/21/13.
 */
@Transactional
public interface IItemDAO
{
    public Item findById(long id);

    public Item save(Item item);

    public void save(List<Item> itemExemplaar);

    List<Item> findAll();

    List<Item> FindByExample(Item b);
}
