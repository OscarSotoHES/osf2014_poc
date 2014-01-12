package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class Citizen extends Model {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "citizen")
	private Long id;
	
	@Required@NotNull
	private String name;
	@Required@NotNull
	private String first_name;
	
	@ManyToOne
	@JoinColumn(name="city_id")
	private City city;
	
	public static Finder<Long,Citizen> find = new Finder<Long, Citizen>(
		    Long.class, Citizen.class
		  );
	
	public Citizen(){}
	
	public Citizen(String first_name, String name){
		this.name = name;
		this.first_name = first_name;
	}
	
	public City getCity(){
		return this.city;
	}
	
	public void setCity(City city){
		this.city = city;
	}
	
	public Long getId(){
		return this.id;
	}
	
	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return this.name;
	}
	
	public void setFirstName(String first_name){
		this.first_name = first_name;
	}

	public String getFirstName(){
		return this.first_name;
	}
	
	public static void create(Citizen citizen){
		citizen.save();
	}
	
	public static void update(Long id){
		find.ref(id).update();
	}
	
	public static void delete(Long id){
		find.ref(id).delete();
	}
	
	public String toString(){
		return first_name+" "+name+"\n";
	}
}
