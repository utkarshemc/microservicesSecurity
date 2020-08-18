/**
 * 
 */
package com.dell.user.constants;

/**
 * @author bhardu
 * @Since May 3, 2020
 */
public interface UserConstant {
	public final String AUTHORIZATION = "Authorization";
	public final String SUCCESFULL_SIGNUP = "The user was succesfully signed up";
	public final String SUCCESFULL_DELETE = "Succesfully Deleted the User:-";

	public final String ERROR_SAVE_DATA = "Unable to save data into Database";
	public final String ERROR_USERNAME_CONFLICT = "The username already exists";
	public final String ERROR_LOGIN = "Username or password is incorrect";
	public final String ERROR_JWT_TOKEN = "Jwt token is not present";
	public final String ERROR_AUTH = "Incorrect Jwt or the auth level is not permissable";
	public final String ERROR_INVALID_USERID = "User with the given id does not exists";

	public final String URL_SIGNUP = "/signup";
	public final String URL_LOGIN = "/login";
	public final String URL_GET_ALL_USERS = "/allUser";
	public final String URL_GET_USER = "/getUser/{id}";
	public final String URL_DELETE_USER = "/deleteUser/{id}";
	public final String URL_GET_USER_BY_JWT = "/getUserByJwt";
	public final String URL_ALL_PAYMENT = "/allPayment";
}
