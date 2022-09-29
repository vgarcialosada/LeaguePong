	package com.leaguepong.controllers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
public class PasswordController {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@CrossOrigin(origins = "*", maxAge = 3600)
	@PostMapping("/{id}/{password}/update_password")
	public ObjectNode updateUser(@PathVariable int id, @PathVariable String password) {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode objectNode = mapper.createObjectNode();
		String newPwd=encryptPassword(password);
		String QUERYupdateUser = "update LEAGUEPONG.USUARIOS set PASSWORD= '"+newPwd+"' where id_usuario= "+id+";";
				try {
				jdbcTemplate.execute(QUERYupdateUser);
				objectNode.put("message", "usuario actualizado");
			} catch (Exception e) {
				objectNode.put("message", "Error al actualizar usuario");
				objectNode.put("error", e.toString());
			}
		 return objectNode;
	}
	
	public static String encryptPassword(String password) {
        String encryptedpassword = null;  
        try   
        {  
            /* MessageDigest instance for MD5. */  
            MessageDigest m = MessageDigest.getInstance("MD5");  
           
            /* Add plain-text password bytes to digest using MD5 update() method. */  
            m.update(password.getBytes());  
              
            /* Convert the hash value into bytes */   
            byte[] bytes = m.digest();  
              
            /* The bytes array has bytes in decimal form. Converting it into hexadecimal format. */  
            StringBuilder s = new StringBuilder();  
            for(int i=0; i< bytes.length ;i++)  
            {  
                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));  
            }  
              
            /* Complete hashed password in hexadecimal format */  
            encryptedpassword = s.toString();  
        }   
        catch (NoSuchAlgorithmException e)   
        {  
            e.printStackTrace();  
        }  
        /* Display the unencrypted and encrypted passwords. */  
        return encryptedpassword;
    }  
	}

