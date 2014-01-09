package org.biblyfx.controllers;

import org.bibly.logic.dao.interfaces.IBoekDAO;
import org.bibly.logic.dao.interfaces.IVergaderruimteDAO;
import org.biblyfx.MainApp;

public class AdminController
{
    private IVergaderruimteDAO vergaderruimteDAO;
    private IBoekDAO boekDAO;

    public AdminController()
    {
        vergaderruimteDAO = (IVergaderruimteDAO) MainApp.applicationContext.getBean("vergaderruimteDAO");
        boekDAO = (IBoekDAO) MainApp.applicationContext.getBean("boekDAO");
    }

}
