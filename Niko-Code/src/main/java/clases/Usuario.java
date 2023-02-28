
package clases;

/**
 *
 * @author HP
 */
public class Usuario {
    private int codigo;
    private String nombre;
    private String contraseña;

    public Usuario(int codigo, String nombre, String contraseña) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.contraseña = contraseña;
    }
    
    public Usuario(){
        
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    
    
}
