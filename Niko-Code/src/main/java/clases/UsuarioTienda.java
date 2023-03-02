/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

/**
 *
 * @author HP
 */
public class UsuarioTienda  extends Usuario {
    private int tienda;
    private String email;

    public UsuarioTienda(int tienda, String email, int codigo, String nombre, String nombreUsuario, String contraseña) {
        super(codigo, nombre, nombreUsuario, contraseña);
        this.tienda = tienda;
        this.email = email;
    }
    public UsuarioTienda(){
    
    }    

    public int getTienda() {
        return tienda;
    }

    public void setTienda(int tienda) {
        this.tienda = tienda;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
}
