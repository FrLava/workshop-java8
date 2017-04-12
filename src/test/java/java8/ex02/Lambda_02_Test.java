package java8.ex02;

import java8.data.Account;
import java8.data.Data;
import java8.data.Person;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * Exercice 02 - Map
 */
public class Lambda_02_Test {

    // tag::PersonToAccountMapper[]
    interface PersonToAccountMapper {
        Account map(Person p);
    }
    interface PersonToStringMapper{
    	String map(Person p);
    }
    interface GenericMapper<T, R>{
    	R map(T objet);
    }
    // end::PersonToAccountMapper[]

    // tag::map[]
    private List<Account> map(List<Person> personList, PersonToAccountMapper mapper) {
        // TODO implémenter la méthode
    	List<Account> compte=new ArrayList<>();
    	
    	for(Person p:personList)
    	{
    		compte.add(mapper.map(p));
    	}
    	
        return compte;
    }
    // end::map[]

    private List<String> map2(List<Person> personList,GenericMapper<Person, String> mapper) {
        // TODO implémenter la méthode
    	List<String> prenom=new ArrayList<>();
    	
    	for(Person p:personList)
    	{
    		prenom.add(mapper.map(p));
    	}
    	
        return prenom;
    }
    


    // tag::test_map_person_to_account[]
    @Test
    public void test_map_person_to_account() throws Exception {

        List<Person> personList = Data.buildPersonList(100);
        //List<Account> compte=new ArrayList<>();
        // TODO transformer la liste de personnes en liste de comptes
        // TODO tous les objets comptes ont un solde à 100 par défaut
        List<Account> result = map(personList, new PersonToAccountMapper() {
			
			@Override
			public Account map(Person p) {
				Account acc = new Account();
				acc.setOwner(p);
				acc.setBalance(100);
				return acc;
			}
		});
        
List<Account> result2 = map(personList, p ->{
				Account acc = new Account();
				acc.setOwner(p);
				acc.setBalance(100);
				return acc;
			}
		);
        
        
        assertThat(result, hasSize(personList.size()));
        assertThat(result, everyItem(hasProperty("balance", is(100))));
        assertThat(result, everyItem(hasProperty("owner", notNullValue())));
    }
    // end::test_map_person_to_account[]

    // tag::test_map_person_to_firstname[]
    @Test
    public void test_map_person_to_firstname() throws Exception {

        List<Person> personList = Data.buildPersonList(100);

        // TODO transformer la liste de personnes en liste de prénoms
        List<String> result = map2(personList,p ->p.getFirstname());


        assertThat(result, hasSize(personList.size()));
        assertThat(result, everyItem(instanceOf(String.class)));
        assertThat(result, everyItem(startsWith("first")));
    }
    // end::test_map_person_to_firstname[]
}
