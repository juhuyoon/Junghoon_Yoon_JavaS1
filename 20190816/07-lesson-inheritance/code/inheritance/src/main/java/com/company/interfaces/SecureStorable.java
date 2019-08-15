package com.company.interfaces;

public interface SecureStorable extends Storable {

    public void encryptData();

    public void decryptData();
}
