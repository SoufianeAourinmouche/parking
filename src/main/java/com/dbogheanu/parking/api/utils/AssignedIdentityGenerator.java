package com.dbogheanu.parking.api.utils;

import java.io.Serializable;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentityGenerator;

import com.dbogheanu.parking.api.models.interfaces.Identifiable;

public class AssignedIdentityGenerator extends IdentityGenerator {
  
  @Override
  public Serializable generate(SharedSessionContractImplementor s, Object obj) {
    if (obj instanceof Identifiable) {
      Integer id = ((Identifiable)obj).getId();

      if (id != null) {
        return id;
      }
    }
    return super.generate(s, obj);
  }
  
}
