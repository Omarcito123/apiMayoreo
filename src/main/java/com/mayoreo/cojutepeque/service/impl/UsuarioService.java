package com.mayoreo.cojutepeque.service.impl;

import com.mayoreo.cojutepeque.domain.entity.Roles;
import com.mayoreo.cojutepeque.domain.entity.Users;
import com.mayoreo.cojutepeque.domain.repository.RolesRepository;
import com.mayoreo.cojutepeque.domain.repository.UsuarioRepository;
import com.mayoreo.cojutepeque.model.ResponseLogin;
import com.mayoreo.cojutepeque.model.ResponseObject;
import com.mayoreo.cojutepeque.service.IUsuarioService;
import com.mayoreo.cojutepeque.utils.MetodosGenericos;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements IUsuarioService {

    private final UsuarioRepository userRepository;

    private final RolesRepository rolesRepository;

    public UsuarioService(UsuarioRepository userRepository, RolesRepository rolesRepository) {
        this.userRepository = userRepository;
        this.rolesRepository = rolesRepository;
    }

    @Override
    public ResponseObject authenticate(String username, String password) {
        ResponseObject response = new ResponseObject();
        ResponseLogin loginObject = new ResponseLogin();

        try {
            username = decodeBase64(username);
            password = decodeBase64(password);
            Users user = userRepository.authenticate(username, password);

            if(user != null) {
                loginObject.setIduser(user.getIduser());
                loginObject.setIdrol(user.getIdrol());
                loginObject.setFirstname(user.getFirstname());
                loginObject.setUsername(user.getUsername());
                loginObject.setDateborn(user.getDateborn());
                loginObject.setRolname(user.getRolname());

                response.setData(loginObject);
                response.setSuccess(true);
                response.setMessage("Login correcto");
            }else {
                response.setSuccess(false);
                response.setMessage("Usuario o contraseña incorrecta");
            }
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Error " + e.getMessage());
        }

        return response;
    }

    @Override
    public ResponseObject getAllUsers() {
        ResponseObject response = new ResponseObject();

        try {
            List<Users> usersList = userRepository.findAll();
            response.setData(usersList);
            response.setSuccess(true);
            response.setMessage("Login correcto");
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Error " + e.getMessage());
        }

        return response;
    }

    public String decodeBase64(String valor) {
        try {
            Base64.Decoder decoder = Base64.getMimeDecoder();
            byte[] decodedByteArray = decoder.decode(valor);
            String decode_Value = new String(decodedByteArray);
            return decode_Value;
        }catch(Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public ResponseObject saveUser(Users user) {
        ResponseObject response = new ResponseObject();
        Users userExist = new Users();
        MetodosGenericos fechaSal = new MetodosGenericos();

        try {
            Optional<Roles> rol = rolesRepository.findById(user.getIdrol());
            if(rol.isPresent()) {
                userExist = userRepository.findByUserName(user.getUsername());
                if (userExist == null) {
                    user.setDateadd(fechaSal.getFecha());
                    user.setRolname(rol.get().getRolname());
                    Users userSave = userRepository.saveAndFlush(user);
                    response.setData(userSave);
                    response.setMessage("Usuario creado exitosamente");
                    response.setSuccess(true);
                } else {
                    response.setMessage("Por favor utiliza otro username");
                    response.setSuccess(false);
                }
            }else {
                response.setMessage("No se encontro el rol seleccionado");
                response.setSuccess(false);
            }
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Error " + e.getMessage());
        }

        return response;
    }

    @Override
    public ResponseObject updateUser(Users user) {
        ResponseObject response = new ResponseObject();
        MetodosGenericos fechaSal = new MetodosGenericos();

        try {
            Optional<Roles> rol = rolesRepository.findById(user.getIdrol());
            if(rol.isPresent()) {
                Optional<Users> userFind = userRepository.findById(user.getIduser());
                if (userFind.isPresent()) {
                    user.setDatemod(fechaSal.getFecha());
                    user.setIduser(userFind.get().getIduser());
                    user.setRolname(rol.get().getRolname());
                    userRepository.save(user);
                    response.setData(user);
                    response.setMessage("Usuario actualizado exitosamente");
                    response.setSuccess(true);
                } else {
                    response.setMessage("Usuario no pudo ser actualizado");
                    response.setSuccess(false);
                }
            }else {
                response.setMessage("No se encontro el rol seleccionado");
                response.setSuccess(false);
            }
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Error " + e.getMessage());
        }

        return response;
    }

    @Override
    public ResponseObject deleteUser(long iduser) {
        ResponseObject response = new ResponseObject();

        try {
            userRepository.deleteById(iduser);
            response.setSuccess(true);
            response.setMessage("Usuario eliminado correctamente");
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Error " + e.getMessage());
        }

        return response;
    }

    @Override
    public ResponseObject getInfoPerfil(long iduser) {
        ResponseObject response = new ResponseObject();

        try {
            Optional<Users> user = userRepository.findById(iduser);
            if(user.isPresent()) {
                response.setData(user.get());
                response.setSuccess(true);
                response.setMessage("Usuario encontrado correctamente");
            } else {
                response.setSuccess(false);
                response.setMessage("Usuario no encontrado");
            }
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Error " + e.getMessage());
        }

        return response;
    }

    @Override
    public ResponseObject updatePerfilInv(Users user) {
        ResponseObject responseObject = new ResponseObject();
        MetodosGenericos fechaSal = new MetodosGenericos();

        try {
            Optional<Users> userU = userRepository.findById(user.getIduser());
            if(userU.isPresent()) {
                Users updatePerfil = new Users();
                updatePerfil.setIduser(userU.get().getIduser());
                updatePerfil.setFirstname(user.getFirstname());
                updatePerfil.setSecondname(user.getSecondname());
                updatePerfil.setSurname(user.getSurname());
                updatePerfil.setSecondsurname(user.getSecondsurname());
                updatePerfil.setEmail(user.getEmail());
                updatePerfil.setPass(userU.get().getPass());
                updatePerfil.setPhone(user.getPhone());
                updatePerfil.setUsername(userU.get().getUsername());
                updatePerfil.setDateborn(user.getDateborn());
                updatePerfil.setIduseradd(userU.get().getIduseradd());
                updatePerfil.setDateadd(userU.get().getDateadd());
                updatePerfil.setIdusermod(userU.get().getIdusermod());
                updatePerfil.setDatemod(userU.get().getDatemod());
                updatePerfil.setIdrol(userU.get().getIdrol());
                Users save = userRepository.save(updatePerfil);
                responseObject.setData(save);
                responseObject.setMessage("Perfil actualizado exitosamente");
                responseObject.setSuccess(true);
            }else {
                responseObject.setMessage("El perfil no pudo ser actualizado no es correcta");
                responseObject.setSuccess(false);
            }
        }catch(Exception e) {
            responseObject.setMessage(e.getMessage());
            responseObject.setSuccess(false);
        }
        return responseObject;
    }

    @Override
    public ResponseObject changePass(long id, String passActual, String newPass) {
        ResponseObject responseObject = new ResponseObject();

        try {
            Users userU = userRepository.changePass(id, passActual);
            if(userU != null) {
                userU.setPass(newPass);
                userRepository.save(userU);
                responseObject.setMessage("Contraseña actualizada exitosamente");
                responseObject.setSuccess(true);
            }else {
                responseObject.setMessage("La contaseña actual ingresa no es correcta");
                responseObject.setSuccess(false);
            }
        }catch(Exception e) {
            responseObject.setMessage(e.getMessage());
            responseObject.setSuccess(false);
        }
        return responseObject;
    }
}
