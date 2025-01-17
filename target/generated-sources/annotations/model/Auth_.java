package model;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.processing.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Auth.class)
public abstract class Auth_ {

	public static volatile SingularAttribute<Auth, String> password;
	public static volatile SingularAttribute<Auth, String> ID;
	public static volatile SingularAttribute<Auth, User> user;
	public static volatile SingularAttribute<Auth, String> username;

	public static final String PASSWORD = "password";
	public static final String I_D = "ID";
	public static final String USER = "user";
	public static final String USERNAME = "username";

}

