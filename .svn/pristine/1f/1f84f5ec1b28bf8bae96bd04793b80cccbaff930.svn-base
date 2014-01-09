package org.bibly.logic.exceptions;

import org.bibly.logic.models.enums.PersoonType;

@SuppressWarnings("serial")
public class LidPermissionException extends Exception
{
    private PersoonType gegevenPersoonType;
    private PersoonType vereistPersoonType;

    public LidPermissionException(PersoonType gegevenPersoonType, PersoonType vereistPersoonType)
    {
        this.gegevenPersoonType = gegevenPersoonType;
        this.vereistPersoonType = vereistPersoonType;
    }

    public PersoonType getGegevenPersoonType()
    {
        return gegevenPersoonType;
    }

    public void setGegevenPersoonType(PersoonType gegevenPersoonType)
    {
        this.gegevenPersoonType = gegevenPersoonType;
    }

    public PersoonType getVereistPersoonType()
    {
        return vereistPersoonType;
    }

    public void setVereistPersoonType(PersoonType vereistPersoonType)
    {
        this.vereistPersoonType = vereistPersoonType;
    }

}
