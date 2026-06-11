package model;

public class Contato {
    private int id;
    private String nome;
    private String telefone;
    private String celular;
    private String email;
    private String endereco;
    private String dataNascimento;
    private String empresa;
    private String cargo;
    private String observacoes;
    private String tipoContato;
    private String cpf;
    private int grupoId;
    private String nomeGrupo;

    public Contato() {}

    private Contato(Builder b) {
        this.id=b.id; this.nome=b.nome; this.telefone=b.telefone;
        this.celular=b.celular; this.email=b.email; this.endereco=b.endereco;
        this.dataNascimento=b.dataNascimento; this.empresa=b.empresa;
        this.cargo=b.cargo; this.observacoes=b.observacoes;
        this.tipoContato=b.tipoContato; this.cpf=b.cpf; this.grupoId=b.grupoId;
    }

    public static class Builder {
        private int id; private String nome; private String telefone;
        private String celular; private String email; private String endereco;
        private String dataNascimento; private String empresa; private String cargo;
        private String observacoes; private String tipoContato; private String cpf; private int grupoId;

        public Builder id(int id){this.id=id;return this;}
        public Builder nome(String nome){this.nome=nome;return this;}
        public Builder telefone(String telefone){this.telefone=telefone;return this;}
        public Builder celular(String celular){this.celular=celular;return this;}
        public Builder email(String email){this.email=email;return this;}
        public Builder endereco(String endereco){this.endereco=endereco;return this;}
        public Builder dataNascimento(String dataNascimento){this.dataNascimento=dataNascimento;return this;}
        public Builder empresa(String empresa){this.empresa=empresa;return this;}
        public Builder cargo(String cargo){this.cargo=cargo;return this;}
        public Builder observacoes(String observacoes){this.observacoes=observacoes;return this;}
        public Builder tipoContato(String tipoContato){this.tipoContato=tipoContato;return this;}
        public Builder cpf(String cpf){this.cpf=cpf;return this;}
        public Builder grupoId(int grupoId){this.grupoId=grupoId;return this;}
        public Contato build(){return new Contato(this);}
    }

    public int getId(){return id;} public void setId(int id){this.id=id;}
    public String getNome(){return nome;} public void setNome(String n){this.nome=n;}
    public String getTelefone(){return telefone;} public void setTelefone(String t){this.telefone=t;}
    public String getCelular(){return celular;} public void setCelular(String c){this.celular=c;}
    public String getEmail(){return email;} public void setEmail(String e){this.email=e;}
    public String getEndereco(){return endereco;} public void setEndereco(String e){this.endereco=e;}
    public String getDataNascimento(){return dataNascimento;} public void setDataNascimento(String d){this.dataNascimento=d;}
    public String getEmpresa(){return empresa;} public void setEmpresa(String e){this.empresa=e;}
    public String getCargo(){return cargo;} public void setCargo(String c){this.cargo=c;}
    public String getObservacoes(){return observacoes;} public void setObservacoes(String o){this.observacoes=o;}
    public String getTipoContato(){return tipoContato;} public void setTipoContato(String t){this.tipoContato=t;}
    public String getCpf(){return cpf;} public void setCpf(String cpf){this.cpf=cpf;}
    public int getGrupoId(){return grupoId;} public void setGrupoId(int g){this.grupoId=g;}
    public String getNomeGrupo(){return nomeGrupo;} public void setNomeGrupo(String n){this.nomeGrupo=n;}
}
