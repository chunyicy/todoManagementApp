# Todo Management App

## I used Spring Boot to build REST APIs for this Todo Management Application (Backend) project:

-	Build Add Todo REST API
-	Build Get Todo by Id REST API
-	Build Get All Todos REST API
-	Build Update Todo by Id REST API
-	Build Delete Todo by Id REST API
-	Build Mark Todo as Complete REST API
-	Build Mark Todo as In-Complete REST API
-	Build Register REST API
-	Build Login REST API
-	Custom Exception Handling
-	Secure REST APIs using JWT (JSON Web Token)

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

#### Using ModelMapper Library:
Use third party mapping library to automatically convert DTO into JPA entity and vice versa. ModelMapper is used for mapping between TodoDto (Data Transfer Object) and Todo (JPA entity).

<br/>

![modelMapper](https://github.com/chunyicy/todoManagementApp/assets/116086176/4a6a050f-e1cf-4e9b-94e5-923e24409a88)

<br/>

### 1.	Build Add Todo REST API

<br/>

#### Todo Service Interface:
Define methods for performing business logic operations. This method signature declares a single method addTodo that takes a TodoDto object as a parameter and returns a TodoDto object.

<br/>

![todoServiceInterface](https://github.com/chunyicy/todoManagementApp/assets/116086176/20b45123-e59c-4669-8bcf-32684961a666)

<br/>

#### TodoServiceImpl class:
TodoServiceImpl implements the TodoService interface and provides an implementation for the addTodo() method.

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
