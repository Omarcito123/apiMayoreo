package com.mayoreo.cojutepeque.service;

import com.mayoreo.cojutepeque.domain.entity.Users;
import com.mayoreo.cojutepeque.model.ResponseObject;

public interface IUsuarioService {
    ResponseObject authenticate(String username, String password);
    ResponseObject getAllUsers();
    String decodeBase64(String valor);
    ResponseObject saveUser(Users user);
    ResponseObject updateUser(Users user);
    ResponseObject deleteUser(long iduser);
    ResponseObject getInfoPerfil(long iduser);
    ResponseObject updatePerfilInv(Users user);
    ResponseObject changePass(long id, String passActual, String newPass);
}
