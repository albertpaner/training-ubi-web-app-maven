package model.dto;

/**
 * This class represents a Data Transfer Object for the evaluator identity
 * and the number of users valued by the evaluator.
 * */
public class EvalCountDto {

    private int valutatoreId;
	private String nome;
    private String cognome;
    private String email;
    private int count = 0;

	public EvalCountDto() {
	}

	public EvalCountDto(int valutatoreId, String nome, String cognome, String email, int count) {
		this.valutatoreId = valutatoreId;
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.count = count;
	}

	public int getValutatoreId() {
		return valutatoreId;
	}

	public void setValutatoreId(int valutatoreId) {
		this.valutatoreId = valutatoreId;
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

	/**
	 * This method gives a string representation of the EvalCountDTO object
	 *
	 * @return a string representation of the DTO object
	 * */
	@Override
	public String toString() {
		return " [ evaluator=" + nome + cognome + ", email=" + email
				+ ", number_users_valued=" + count + "]";
	}
    
}