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
-	Exception Handling - Create Custom Exception to deal with errors and exceptions
-	Implement GlobalExceptionHandler to handle exceptions globally and to centralize exception handling 
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

