package model;
public class DetalhesContato {
    private int id; private int contatoId; private String cpf; private String celular; private String redeSocial;
    public int getId(){return id;} public void setId(int id){this.id=id;}
    public int getContatoId(){return contatoId;} public void setContatoId(int c){this.contatoId=c;}
    public String getCpf(){return cpf;} public void setCpf(String cpf){this.cpf=cpf;}
    public String getCelular(){return celular;} public void setCelular(String c){this.celular=c;}
    public String getRedeSocial(){return redeSocial;} public void setRedeSocial(String r){this.redeSocial=r;}
}
