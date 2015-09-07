/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaontologia.entidade.antigos;

/**
 *
 * @author Willian
 */
public class Livro {
    private String autor;
    private String titulo;
    private String edicao;
    private String editora;
    private String cidade;
    private String ano;
    private String paginas;

    public String getCidade() {
        return cidade;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public void setPaginas(String paginas) {
        this.paginas = paginas;
    }
    
    
    public String getAutor() {
        return autor;
    }

    public String getAno() {
        return ano;
    }

    public String getEditora() {
        return editora;
    }

    public String getPaginas() {
        return paginas;
    }

    public String getEdicao() {
        return edicao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setEdicao(String edicao) {
        this.edicao = edicao;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setCidade(String c) {
        this.cidade = c;
    }  
}
