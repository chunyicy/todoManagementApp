<br/>

# Todo Management App

<br/>

## I used Spring Boot to build REST APIs for this Todo Management Application (Backend) Project:

-	Build Add Todo REST API
-	Build Get Todo by Id REST API
-	Build Get All Todos REST API
-	Build Update Todo by Id REST API
-	Build Delete Todo by Id REST API
-	Build Mark Todo as Complete REST API
-	Build Mark Todo as In-Complete REST API
-	Build Register REST API
-	Build Login REST API
-	Exception Handling - Create Custom Exception to deal with errors and exceptions
-	Implement GlobalExceptionHandler to handle exceptions globally and to centralize exception handling
-	Implement method-level security using role-based authorization, protecting REST APIs from unauthroized access based on user roles
-	Implementing database authentication involves setting up Spring Security to authenticate users against a database rather than using in-memory authentication
-	Secure REST APIs using JWT (JSON Web Token)
-	Configure Spring Security to use JWT for authentication and authorization

<br/>

## Technology Stack:
-	Spring Boot 3
-	Spring Security 6
-	JWT
-	Spring Data JPA (Hibernate 6)
-	Maven
-	Postman Client
-	MySQL Database
-	IntelliJ IDEA

<br/>

## Build REST APIs

<br/>

#### Create Todo JPA Entity:

<br/>

![createTodoJpaEntity](https://github.com/chunyicy/todoManagementApp/assets/116086176/9b72771d-c39c-49ce-8476-a0b286b40a1b)

<br/>

#### Create TodoRepository interface:
This interface extends JpaRepository to get CRUD methods to perform CRUD database operations on the Todo entity.

<br/>

![todoRepositoryInterface](https://github.com/chunyicy/todoManagementApp/assets/116086176/573712fb-c4b2-4bc5-ab66-28fc8cceddc7)

<br/>

#### Create TodoDto class:
If return the Todo entity as a response of the REST API to the client, then there may be chance that the entity may contain the sensitive information like password. Instead of returning entity directly to the client, create a TodoDto class to transfer the data between client and server.

<br/>

![todoDto](https://github.com/chunyicy/todoManagementApp/assets/116086176/b27e7618-9590-44a9-8290-b79377170636)

<br/>

#### Using ModelMapper library:
Use third party mapping library to automatically convert DTO into JPA entity and vice versa. ModelMapper is used for mapping between TodoDto (Data Transfer Object) and Todo (JPA entity).

<br/>

![modelMapper](https://github.com/chunyicy/todoManagementApp/assets/116086176/4a6a050f-e1cf-4e9b-94e5-923e24409a88)

<br/>

### 1.	Build Add Todo REST API

<br/>

#### Todo Service Interface:
Define methods for performing business logic operations within TodoService interface. This method signature declares a single method addTodo that takes a TodoDto object as a parameter and returns a TodoDto object.

<br/>

![todoServiceInterface](https://github.com/chunyicy/todoManagementApp/assets/116086176/20b45123-e59c-4669-8bcf-32684961a666)

<br/>

#### TodoServiceImpl class:
TodoServiceImpl class implements the TodoService interface and provides an implementation for the addTodo() method.

<br/>

![todoServiceImpl](https://github.com/chunyicy/todoManagementApp/assets/116086176/1b7c4219-0af7-4c01-a3d6-353668a31328)

<br/>

#### Create TodoController class and build Add Todo REST API within TodoController class:
@RestController annotation make this class as Spring MVC rest controllers, define the REST APIs within this spring MVC rest controller.

<br/>

![TodoController](https://github.com/chunyicy/todoManagementApp/assets/116086176/a970fd04-18e2-494b-bdd3-26d41e18047c)

<br/>

#### Test Add Todo REST API using Postman client:

<br/>

![addTodoPostman](https://github.com/chunyicy/todoManagementApp/assets/116086176/0ebaf818-1ad5-4403-8cbb-0ed22e57c704)

<br/>
<br/>

### 2. Build Get Todo REST API

<br/>

#### TodoService interface:
Define getTodo() method within TodoService interface.

<br/>

![getTodoTodoService](https://github.com/chunyicy/todoManagementApp/assets/116086176/d6cb9dde-a0db-4527-9c50-2157c34e8a4b)

<br/>

#### TodoServiceImpl class:
Implement this getTodo() method within TodoServiceImpl class. ModelMapper to map the Todo entity object retrieved from the database to a TodoDto object.

<br/>

![getTodoTodoServiceImpl](https://github.com/chunyicy/todoManagementApp/assets/116086176/71f677c5-563a-44a1-881d-51973f1b476b)

<br/>

#### TodoController class:
Build Get Todo REST API within TodoController class.

<br/>

![getTodoTodoController](https://github.com/chunyicy/todoManagementApp/assets/116086176/484da277-2caa-4f37-b437-16b6fcc4f865)

<br/>

#### Test Get Todo REST API using Postman client:

<br/>

![getTodoPostman](https://github.com/chunyicy/todoManagementApp/assets/116086176/5d158cb7-bf0d-4ae2-9352-16ce51600329)

<br/>

If Get Todo is not found in database, Postman client got the response of the API and return HTTP status code of 500 internal server error.

<br/>

![getTodoPostman500status](https://github.com/chunyicy/todoManagementApp/assets/116086176/592278c2-1712-45d3-8ff0-ecbcd994409f)

<br/>

#### Create ResourceNotFoundException to handle Todo object not found in database:
Create custom exception to handle Todo object not found in database. This REST API should return 404 Not Found HTTP Status.
Create a custom exception named ResourceNotFoundException and extend this class from RuntimeException. Throw this exception if Todo record with a given Id is not exist in database.

<br/>

![customException](https://github.com/chunyicy/todoManagementApp/assets/116086176/9f0e26aa-6f17-49bd-84be-bb336e8d5f64)

<br/>

#### Create GlobalExceptionHandler class to handle the custom exception (ResourceNotFoundException) globally:
ResponseEntity: The method returns a ResponseEntity<String>, which allows to customize the HTTP response status and body that will be returned to the client.

<br/>

![gloablException](https://github.com/chunyicy/todoManagementApp/assets/116086176/58899f2d-e0f7-4872-ac06-abf3066cd6d3)

<br/>

#### TodoServiceImpl class:
Customize error message ("Todo not found with id: " + todoId).

<br/> 

![todoServiceImpl](https://github.com/chunyicy/todoManagementApp/assets/116086176/a13be051-cfb8-445c-a01e-6b90aa56ac8c)

<br/>


#### Test Todo not found with given id using Postman client:

<br/>

![todoNotfound](https://github.com/chunyicy/todoManagementApp/assets/116086176/1f4fdb00-db5b-4807-9c2a-e19a2a1d3897)

<br/>

<br/>

### 3. Build Get All Todos REST API

<br/>

#### TodoService interface:
Define getAllTodos() method within TodoService interface.

<br/>

![getAllTodoServiceInterface](https://github.com/chunyicy/todoManagementApp/assets/116086176/ee5f8ae5-94cc-4db7-ac8d-d152783a48a0)

<br/>

#### TodoServiceImpl class:
Implements getAllTodos() method within TodoServiceImpl class, getAllTodos() method responsible for retrieving all Todo entities from the database.

<br/>

![getAllTodoServiceImpl](https://github.com/chunyicy/todoManagementApp/assets/116086176/79ea86d5-d7f5-4682-a6d6-fc27167786c0)

<br/>

#### TodoController class:
Build Get All Todos REST API within TodoController class. 

<br/>

![getAllTodoController](https://github.com/chunyicy/todoManagementApp/assets/116086176/982b0e58-951f-40c0-bf3b-df052ec95ee6)

<br/>

#### Test Get All Todos REST API using Postman Client:

<br/>

![getAllTodoPostman](https://github.com/chunyicy/todoManagementApp/assets/116086176/c2a5f247-bc08-449f-a85b-9ccd1cc30b78)

<br/>

<br/>

### 4. Build Update Todo REST API

<br/>

#### TodoService interface:
Define updateTodo() method within TodoService interface.

<br/>

![updateTodoServiceInterface](https://github.com/chunyicy/todoManagementApp/assets/116086176/707eed80-b8ed-41df-a7c1-fb7245fee84f)

<br/>

#### TodoServiceImpl class:
Implement updateTodo() method within the TodoServiceImpl class. 
If the Todo entity is not found (orElseThrow()), it throws a ResourceNotFoundException with a message indicating the id of the Todo that was not found.
Saves the updated todo entity back into the database using todoRepository.save().
Maps the updated Todo entity (updatedTodo) to a TodoDto object using modelMapper and returns it.

<br/>

![updateTodoServiceImpl](https://github.com/chunyicy/todoManagementApp/assets/116086176/c9496e0c-4cc0-4b10-b23f-4a1b4d70a997)

<br/>

#### TodoController class:
Build Update Todo REST API within the TodoController class.

<br/>

![updateTodoController](https://github.com/chunyicy/todoManagementApp/assets/116086176/a7c80d00-b4e7-4353-98e1-7df7ba4a7070)

<br/>

#### Test Update Todo REST API using Postman client:

<br/>

![updateTodoPostman](https://github.com/chunyicy/todoManagementApp/assets/116086176/a3227568-9a77-428c-80f5-2b20a653c70d)

![updateTodoPostman2](https://github.com/chunyicy/todoManagementApp/assets/116086176/2882826e-1545-48ce-8b8f-a86fd74d5308)

<br/>

<br/>

### 5. Build Delete Todo REST API

<br/>

#### TodoService interface:
Define deleteTodo() method within TodoService interface.

<br/>

![deleteTodoServiceInterface](https://github.com/chunyicy/todoManagementApp/assets/116086176/20f60953-0043-43eb-bc03-735b0598c21e)

<br/>

#### TodoServiceImpl class:
Implement deleteTodo() method with the TodoServiceImpl class.

<br/>

![deleteTodoServiceImpl](https://github.com/chunyicy/todoManagementApp/assets/116086176/e5418c0a-4a97-4387-ad62-4d3610098808)

<br/>

#### TodoController class:
Build Delete Todo REST API within TodoController class. Constructs a ResponseEntity with an HTTP status of 200 (OK) and a success message ("Todo deleted successfully!"). This response indicates that the delete operation was successful.

<br/>

![deleteTodoController](https://github.com/chunyicy/todoManagementApp/assets/116086176/46187964-27ad-4c20-a330-7c5c0f843eba)

<br/>

#### 
Test Delete Todo REST API using Postman client:

<br/>

![testDelete](https://github.com/chunyicy/todoManagementApp/assets/116086176/196f5315-5775-4482-b7ea-8cf70c37ca0d)

![testDelete2](https://github.com/chunyicy/todoManagementApp/assets/116086176/da4396d4-73ee-4857-a1ee-dba5f3de7e41)

<br/>

<br/>

### 6. Build Mark Todo as Complete REST API

<br/>

#### TodoService interface:
Define completeTodo() method within TodoService interface. The completeTodo method is responsible for marking a Todo entity as completed by setting its completed flag to true.

<br/>

![completeTodoInterface](https://github.com/chunyicy/todoManagementApp/assets/116086176/878f694c-c36a-49d1-af4b-1e4f12b7d175)

<br/>

#### TodoServiceImpl class:
Implement completeTodo() method within TodoServiceImpl class. The purpose of this method is to mark a specific Todo entity as completed in the database.

<br/>

![completeTodoImpl](https://github.com/chunyicy/todoManagementApp/assets/116086176/7d513625-d022-40e1-a140-9599da517ccb)

<br/>

#### TodoController class:
Build Complete Todo REST API within TodoController class.
Uses @PatchMapping to specify that this method handles partial updates (specifically marking a ‘Todo’ as completed) to existing resources (Todo).

<br/>

![todoController](https://github.com/chunyicy/todoManagementApp/assets/116086176/266f12c6-6b68-4153-860e-caae387b1aa8)

<br/>

#### Testing Mark Todo as Complete REST API using Postman client:

<br/>

![testCompleteTodo](https://github.com/chunyicy/todoManagementApp/assets/116086176/1bcc061f-1361-4471-8c0c-d7e728d75acc)

![testCompleteTodo2](https://github.com/chunyicy/todoManagementApp/assets/116086176/6cb9f488-c3ef-4799-acaf-cfd798770c8d)

<br/>

<br/>

### 7. Build Mark Todo as In-Complete REST API

<br/>

#### TodoService interface:
Define inCompleteTodo() method within TodoService interface.

<br/>

![incompleteTodoInterface](https://github.com/chunyicy/todoManagementApp/assets/116086176/c889617a-5570-4abe-86ba-97c7a5ea741e)

<br/>

#### Implement inCompleteTodo() method within TodoServiceImpl class:
The purpose of this method is to mark a specific Todo entity as incomplete by setting its completed flag to false in the database. It ensures that if the Todo with the specified todoId does not exist, a ResourceNotFoundException is thrown to indicate the absence of the entity.

<br/>

![inCompleteServiceImpl](https://github.com/chunyicy/todoManagementApp/assets/116086176/f586dc2b-af75-4e88-a18d-d6d41ca0aea7)

<br/>

#### Build In-Complete Todo REST API within TodoController class:
The inCompleteTodo() method in the service layer is responsible for updating the Todo entity identified by ‘todoId’, marking it as incomplete through PATCH request and by using a dedicated endpoint (/todos/{todoId}/in-complete).

<br/>

![inCompleteTodoController](https://github.com/chunyicy/todoManagementApp/assets/116086176/3fd68a12-7fc6-45d5-aa27-4ed068dd270f)

<br/>

#### Test In-Complete Todo REST API using Postman client:

<br/>

![testInComplete](https://github.com/chunyicy/todoManagementApp/assets/116086176/4a89620e-5b19-4bc7-bc04-8ecfa9deb21e)

![testInComplete2](https://github.com/chunyicy/todoManagementApp/assets/116086176/bc44c566-84fe-41a1-83b9-498446f4fbaf)


<br/>
<br/>

## Secure REST API using JWT authentication

<br/>

Add JWT related maven dependencies. Generate a JWT secret key and JWT expiration date. Encrypt secret key using 256 SHA Algorithm. Convert 7 days to milliseconds and use it as expiration date, JWT token will expire after 7 days.

<br/>

![dependencies](https://github.com/chunyicy/todoManagementApp/assets/116086176/7d038d37-a410-452d-8437-a2b924b9d8bd)


![secretkey](https://github.com/chunyicy/todoManagementApp/assets/116086176/fba58157-ae96-42d2-9505-c3cd2cff6333)


<br/>

#### Create JwtAuthenticationEntryPoint class:
This class handles authentication failures when users try to access secured resources without proper authentication credentials. Exception is thrown due to unauthorized user trying to access a resource that requires authentication. The AuthenticationEntryPoint interface provides strategy for handling unsuccessful authentication attempts.

<br/>

![jwtAuthenticationEntryPointclass](https://github.com/chunyicy/todoManagementApp/assets/116086176/26e23066-8ba4-4bda-be07-2844ab59d2e0)

<br/>

#### Create JwtTokenProvider class:

JwtTokenProvider class is responsible for generating JWT tokens, extracting information from tokens, and validating tokens.

<br/>

Generating Tokens: Used when a user logs in successfully (‘generateToken’).
Validating Tokens: Ensures that incoming requests have valid JWT Tokens (‘validateToken’).
Extracting User Information: Retrieves the username from a JWT token (‘getUsername’)

![jwtTokenProviderclass](https://github.com/chunyicy/todoManagementApp/assets/116086176/554e506e-9e0c-44d2-983e-c120df8d84e0)

![jwtTokenProviderclass2](https://github.com/chunyicy/todoManagementApp/assets/116086176/1a2c6f9c-7226-44fb-8b6d-7084eeb353f5)

<br/>

#### Create JwtAuthenticationFilter class:
Create JwtAuthenticationFilter class to authenticate the JWT token. This class intercepts incoming requests, extract and validate JWT tokens from the Authorization header, and sets up Spring Security's authentication context based on the validated token.

<br/>

![jwtAuthenticationFilterclass](https://github.com/chunyicy/todoManagementApp/assets/116086176/9d3275e1-8bb2-4154-9f0f-d7bac69b2325)


![jwtAuthenticationFilterclass2](https://github.com/chunyicy/todoManagementApp/assets/116086176/a65f00d9-c7d6-4fe2-813a-5e59f978ee3b)


![jwtAuthenticationFilterclass3](https://github.com/chunyicy/todoManagementApp/assets/116086176/4ea6f9e6-3ee8-4bc8-81ec-bc3223601739)


<br/>

<br/>

#### Create JwtAuthResponse class:
The JwtAuthResponse class is a straightforward representation of the response structure when issuing JWT tokens. 

![jwtAuthResponseclass](https://github.com/chunyicy/todoManagementApp/assets/116086176/fc4be3b8-ebfb-44d6-a9e3-7e0a796e4d27)

<br/>

<br/>

#### Configure JWT in SpringSecurityConfig class:
This class is to handle JWT-based authentication for different endpoints. 
This setup ensures that:
Authentication and authorization are configured properly.
JWT-based authentication is handled before the standard username-password authentication.
Method-level security (@PreAuthorize, etc.) is enabled with @EnableMethodSecurity.


![springSecurityConfig](https://github.com/chunyicy/todoManagementApp/assets/116086176/026091c9-74f2-490d-a168-eb2a00d21cd4)


![springSecurityConfig2](https://github.com/chunyicy/todoManagementApp/assets/116086176/424f5665-845e-40e6-8c4c-8effa26d7c99)


<br/>

<br/>


### Implement method-level security using role-based authorization:

By using @PreAuthorize with hasRole or hasAnyRole, you can control access to methods based on the roles of authenticated users in your Spring application.
Achieve the role based authroization using method level security.
In order to implement the method level security, we have to use two important annotations that is @EnableMethodSecurity annotation and @PreAuthorize annotation.


![methodLevel](https://github.com/chunyicy/todoManagementApp/assets/116086176/6d102504-0f32-4a01-baa3-6089693508dd)

<br/>

### Implementing database authentication:

<br/>

#### Creating User and Role JPA Entities and store them in MySQL database:

![userEntity](https://github.com/chunyicy/todoManagementApp/assets/116086176/0e8903d9-3704-4a39-b658-987f1951a95d)


![roleEntity](https://github.com/chunyicy/todoManagementApp/assets/116086176/380a3557-46b1-486b-9a7b-c00c92ac2421)


<br/>

#### Creating UserRepository and RoleRepository:


![userRepo](https://github.com/chunyicy/todoManagementApp/assets/116086176/a7c36484-bfe0-4f0e-8b84-cc3637d01216)


![roleRepo](https://github.com/chunyicy/todoManagementApp/assets/116086176/1bb6ebe6-7f9e-4d5c-a41f-43d96b4854c4)


<br/>

#### Creating CustomUserDetailsService class:
The CustomUserDetailsService class integrates with Spring Security to provide custom logic for loading user details from a database (via UserRepository). It retrieves user information based on username or email, maps user roles to Spring Security GrantedAuthority objects, and constructs a UserDetails object used for authentication and authorization within a Spring Security-enabled application.

![customUserDetailsclass](https://github.com/chunyicy/todoManagementApp/assets/116086176/70d54388-bc13-4a32-9681-6552772eb317)

<br/>

#### Generating encoding password:

![passwordEncodingImpl](https://github.com/chunyicy/todoManagementApp/assets/116086176/4c1d0206-2c74-4bd1-a5f7-7317dc72c7d7)

<br/>

#### MySQL Database - users table, roles table, users_roles table:


![databaseUser](https://github.com/chunyicy/todoManagementApp/assets/116086176/0e8d94f9-7ec2-4b24-97ed-d02b27f6cba8)


![databaseRole](https://github.com/chunyicy/todoManagementApp/assets/116086176/09857950-3900-4be0-82af-3bc2c8cfbaff)


![joinTable](https://github.com/chunyicy/todoManagementApp/assets/116086176/c8bd6c5f-b11a-45bd-b9bc-5e36623d53c7)

<br/>
<br/>

### 8. Build Register Register API

#### Create RegisterDto Class:

![registerDto](https://github.com/chunyicy/todoManagementApp/assets/116086176/b08dccfd-d86d-47aa-9212-742f904eeab6)


<br/>

#### Create AuthService interface and define register() method:

![authServiceInterface](https://github.com/chunyicy/todoManagementApp/assets/116086176/89c0dab8-1f57-4eb7-9342-b5c6d613e4d2)


<br/>

#### Create AuthServiceImpl class:


![authServiceImpl](https://github.com/chunyicy/todoManagementApp/assets/116086176/86be6de1-3a2d-4bf9-ac8e-1fa3b3a55354)


<br/>

#### Create AuthController and build Register REST API:

![authController](https://github.com/chunyicy/todoManagementApp/assets/116086176/18f21daf-9303-4de0-a800-07f403fc8e81)


<br/>

#### Create Custom Exception Handling:

![exceptionHandling](https://github.com/chunyicy/todoManagementApp/assets/116086176/6e2829bf-18e4-449e-9691-ba97bfc41de5)

![exceptionHandling1](https://github.com/chunyicy/todoManagementApp/assets/116086176/f8afd905-c87f-4b4e-8797-3b56e59ef05f)

![exceptionHandling2](https://github.com/chunyicy/todoManagementApp/assets/116086176/a848b850-518b-439d-a6f0-b6eda9dbfbfd)


<br/>

#### Test Register REST API using Postman Client:


![testRegister](https://github.com/chunyicy/todoManagementApp/assets/116086176/67cf3fb9-ca4e-484e-b6fd-8f7f0cf1ec80)


![userDatabase](https://github.com/chunyicy/todoManagementApp/assets/116086176/d65bbd62-c0ca-4294-8046-3cabcd8c7f7b)


<br/>

#### Test Register REST API with username and email already exist in database:

Return error message of username already exists.

![testRegisterUsernameExists](https://github.com/chunyicy/todoManagementApp/assets/116086176/a731d12f-685d-4b8c-87ca-531f441f4fb4)

<br/>

Return error message of email already exists.

![testRegisterEmailExists](https://github.com/chunyicy/todoManagementApp/assets/116086176/be9f283e-bd7f-4f98-8624-d4af8f35f464)


<br/>

<br/>


### 9. Build Login REST API

#### Create LoginDto class:

![loginDto](https://github.com/chunyicy/todoManagementApp/assets/116086176/76f4de67-89ea-43fe-a1f6-2432cc78a9e2)

<br/>

#### Define login() method in AuthService interface:

![authServiceInterface](https://github.com/chunyicy/todoManagementApp/assets/116086176/c075c055-db52-45eb-93dd-31834fda9010)


<br/>

#### Implement login() method in AuthServiceImpl class:

![authServiceImpl](https://github.com/chunyicy/todoManagementApp/assets/116086176/58dc086e-bb2f-44c3-a5c2-4f8c4108dbb4)


<br/>

#### Build Login REST API in AuthController class:

![authController](https://github.com/chunyicy/todoManagementApp/assets/116086176/22cf4cfc-ae43-4a69-85b2-d212d6e851de)


<br/>

#### Test Login REST API using Postman Client:

![testLogin](https://github.com/chunyicy/todoManagementApp/assets/116086176/fb3f33d5-b6cb-4c5c-bb1b-a8bedfb0dd1f)

#### Test Non-User Login:

Return error message : user not exists by username or email.

![testNonUserLogin](https://github.com/chunyicy/todoManagementApp/assets/116086176/a11593d0-a451-4832-8b25-0dc6313f0545)

<br/>

## User Role can access Get Todo, Get All Todo, Mark Todo as Complete, Mark Todo as In-Complete but NOT AUTHORIZED to (Add Todo, Update Todo and Delete Todo):

User login:

![login](https://github.com/chunyicy/todoManagementApp/assets/116086176/02fa166c-f39e-40c4-86d9-6f360a092905)

![loginToken](https://github.com/chunyicy/todoManagementApp/assets/116086176/84ecde4e-f665-40be-b5fb-5c66308705af)

<br/>

User cannot perform Add Todo, Update Todo and Delete Todo:

![userAddTodo](https://github.com/chunyicy/todoManagementApp/assets/116086176/2f4ee8fd-4b36-4031-9ad2-d24706094bc9)

![userUpdateTodo](https://github.com/chunyicy/todoManagementApp/assets/116086176/f86d01aa-9922-44ed-baa2-1c5d6964db15)


![userDeleteTodo](https://github.com/chunyicy/todoManagementApp/assets/116086176/07b8a63f-5e42-48bf-ad3d-6ac4a21ff0b9)

<br/>

User can Get Todo, Get All Todo, Mark Todo as Complete and Mark Todo as In-Complete:

![getTodobyId](https://github.com/chunyicy/todoManagementApp/assets/116086176/f2b5f06d-725f-44f3-a25b-6ecd0e24262a)


![getTodo](https://github.com/chunyicy/todoManagementApp/assets/116086176/85e7606f-031c-404e-8807-43d7e70ca781)

![completeTodo](https://github.com/chunyicy/todoManagementApp/assets/116086176/6ba5961f-ee4d-417f-b0f6-3378937924b3)


![inCompleteTodo](https://github.com/chunyicy/todoManagementApp/assets/116086176/6883e30f-3dc8-4d0c-a641-834d2c742f11)


<br/>

<br/>


## Admin Role can Add Todo, Update Todo, Delete Todo, Get Todo, Get All Todo, Mark Todo as Complete and Mark Todo as In-Complete:

Admin Login:

![adminLogin](https://github.com/chunyicy/todoManagementApp/assets/116086176/b0171e5b-2079-4355-8474-e39678d8fa49)


![adminLoginToken](https://github.com/chunyicy/todoManagementApp/assets/116086176/439612a2-82d6-4b97-b92e-32b6f729c6ce)


Add Todo, Update Todo and Delete Todo:

![addTodo](https://github.com/chunyicy/todoManagementApp/assets/116086176/5ece1013-e4c9-43f9-b2ae-392c30b5fe90)


![updateTodo](https://github.com/chunyicy/todoManagementApp/assets/116086176/bd108190-58d2-4a3f-bd16-d68650629716)


![DeleteTodo](https://github.com/chunyicy/todoManagementApp/assets/116086176/acd245f7-9374-4d9f-ba25-8b3880d01c86)
