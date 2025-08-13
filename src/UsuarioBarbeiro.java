public class UsuarioBarbeiro {
    private String usuario;
    private String senha;
    private Barbeiro barbeiro;

    public UsuarioBarbeiro(String usuario, String senha, Barbeiro barbeiro){
        this.usuario = usuario;
        this.senha = senha;
        this.barbeiro = barbeiro;
    }

    public boolean autenticar(String usuario, String senha){
        return this.usuario.equals(usuario) && this.senha.equals(senha);
    }

    public String getUsuario() {
        return usuario;
    }

    public String getSenha() {
        return senha;
    }

    public Barbeiro getBarbeiro() {
        return barbeiro;
    }

    @Override
    public String toString() {
        return "Usuário: " + usuario + " | " + barbeiro.toString();
    }
}
