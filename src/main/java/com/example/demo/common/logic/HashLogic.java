package com.example.demo.common.logic;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.example.demo.common.Constant;

public class HashLogic {
	
	public static String getHash(String text) throws NoSuchAlgorithmException {
		var inputPasswordByteArray = MessageDigest.getInstance("SHA-256").digest((text + Constant.SALT).getBytes());
        return String.format("%040x", new BigInteger(1, inputPasswordByteArray));
	}
}