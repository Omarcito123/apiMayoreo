package com.mayoreo.cojutepeque.controller;

import com.mayoreo.cojutepeque.domain.entity.Users;
import com.mayoreo.cojutepeque.model.ParamsGeneric;
import com.mayoreo.cojutepeque.model.ResponseObject;
import com.mayoreo.cojutepeque.service.IUsuarioService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/users")
public class UsuarioController {

    private final IUsuarioService userService;

    public UsuarioController(IUsuarioService userService) {
        this.userService = userService;
    }

    @CrossOrigin
    @PostMapping("/login")
    public ResponseObject login(@RequestBody Users userParam) {
        return userService.authenticate(userParam.getUsername(), userParam.getPass());
    }

    @CrossOrigin
    @GetMapping("/getAllUsers")
    public ResponseObject getAllUsers() {
        return userService.getAllUsers();
    }

    @CrossOrigin
    @PostMapping("/getInfoPerfil")
    public ResponseObject getInfoPerfil(@RequestBody Users user) {
        return userService.getInfoPerfil(user.getIduser());
    }

    @CrossOrigin
    @PostMapping("/updatePerfilInv")
    public ResponseObject updatePerfilInv(@RequestBody Users user) {
        return userService.updatePerfilInv(user);
    }

    @CrossOrigin
    @PostMapping("/changePass")
    public ResponseObject changePass(@RequestBody ParamsGeneric params) {
        return userService.changePass(params.getId(), params.getPassActual(), params.getNewPass());
    }

    @CrossOrigin
    @PostMapping("/saveUser")
    public ResponseObject saveUser(@RequestBody Users user) {
        return userService.saveUser(user);
    }

    @CrossOrigin
    @PutMapping("/updateUser")
    public ResponseObject updateUser(@RequestBody Users user) {
        return userService.updateUser(user);
    }

    @CrossOrigin
    @PostMapping("/deleteUser")
    public ResponseObject deleteUser(@RequestBody Users user) {
        return userService.deleteUser(user.getIduser());
    }
}
