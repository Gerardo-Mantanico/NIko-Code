
package clases;

/**
 *
 * @author HP
 */
public class Usuario {
    private int codigo;
    private String nombre;
    private String nombreUsuario;
    private String contraseña;

    public Usuario(int codigo, String nombre, String nombreUsuario, String contraseña) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.nombreUsuario = nombreUsuario;
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

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombre) {
        this.nombreUsuario = nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    
}
