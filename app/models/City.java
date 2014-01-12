package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.validation.constraints.NotNull;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class City extends Model {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "city")
	private Long id;
	
	@Required@NotNull
	private String name;
	@OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="city")
	private List<Citizen> citizen;
	public static Finder<Long,City> find = new Finder<Long, City>(
		    Long.class, City.class
		  );
	
	public City(){
		citizen = new ArrayList<Citizen>();
	}
	
	public City(String name){
		this.name = name;
		citizen = new ArrayList<Citizen>();
	}
	
	public List<Citizen> getCitizen(){
		return this.citizen;
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
	
	public static void create(City city){
		city.save();
	}
	
	public static void update(Long id){
		find.ref(id).update();
	}
	
	public static void delete(Long id){
		find.ref(id).delete();
	}
	
	public String toString(){
		return this.name+"\n";
	}
}
