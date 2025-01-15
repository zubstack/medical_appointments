package model;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.processing.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Patient.class)
public abstract class Patient_ extends model.User_ {

	public static volatile SingularAttribute<Patient, String> birthday;
	public static volatile SingularAttribute<Patient, Double> weight;
	public static volatile SingularAttribute<Patient, String> blood;
	public static volatile SingularAttribute<Patient, Double> height;

	public static final String BIRTHDAY = "birthday";
	public static final String WEIGHT = "weight";
	public static final String BLOOD = "blood";
	public static final String HEIGHT = "height";

}

