package model.Dto;


public class EvalCountDto {

    private int ValutatoreId;
    private String nome;
    private String cognome;
    private String email;
    private int count = 0;

	public EvalCountDto() {
	}

	public EvalCountDto(int valutatoreId, String nome, String cognome, String email, int count) {
		ValutatoreId = valutatoreId;
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.count = count;
	}
    
	public int getValutatoreId() {
		return ValutatoreId;
	}
	public void setValutatoreId(int valutatoreId) {
		ValutatoreId = valutatoreId;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
    
    @Override
	public String toString() {
		return " [ evaluator=" + nome + cognome + ", email=" + email
				+ ", number_users_valued=" + count + "]";
	}
    
}