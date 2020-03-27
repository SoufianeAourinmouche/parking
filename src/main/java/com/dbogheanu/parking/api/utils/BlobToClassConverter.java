package com.dbogheanu.parking.api.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.dbogheanu.parking.api.servicies.interfaces.IParkingFees;

@Converter
public class BlobToClassConverter implements AttributeConverter<IParkingFees, byte[]>
{
    @Override
    public byte[] convertToDatabaseColumn(IParkingFees attribute) {
        byte[] result = null;

        try
        {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(attribute);
            oos.flush();
            result = bos.toByteArray();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public IParkingFees convertToEntityAttribute(byte[] dbData)
    {
      IParkingFees pricingPolicy = null;

        try {
            ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(dbData));
            pricingPolicy = (IParkingFees) in.readObject();
            in.close();
        }
        catch (IOException | ClassCastException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }

        return pricingPolicy;
    }
}
