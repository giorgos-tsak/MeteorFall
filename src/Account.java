import java.io.Serializable;
import java.util.HashMap;

public class Account implements Serializable,Comparable<Account>{

	private String password;
	private String username;
	private int bestScore;
	
	
	Account()
	{
		
	}
	

	@Override
	public int compareTo(Account o) {
		return this.bestScore-o.bestScore;
	}
	
	public int getBestScore() {
		return bestScore;
	}
	public String getPassword() {
		return password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setBestScore(int bestScore) {
		this.bestScore = bestScore;
	}

}
