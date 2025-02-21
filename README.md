# **RoundTwo - Play It Forward**

## **Overview**

RoundTwo is an application that allows users to trade puzzles and board games, in addition, allow users to create gaming events for others to attend. The inspiration and overall purpose of our program is to allow people to move on from old puzzles and games, and to spark excitement in the next owner. We apsire to bring the gaming community closer by creating the opportunity for enthusiasts to gather.


## **Meet Our Team**
- Gabby (Repository Owner)
- Ray (Code Master General)
- Vidal (Database Administrator)
- Will (SCRUM Master)


### Authenticate
| HTTP Verb | URI                                         | Request Body                             | Response Body                                  | Response Codes  |
|-----------|---------------------------------------------|------------------------------------------|------------------------------------------------|-----------------|
| GET       | `/api/register`                             |                                          | Register a new user account                    | 200, 400        |
| GET       | `/api/authenticate`                         |                                          | Verify authorized user account                 | 200, 401        |

### User
| HTTP Verb | URI                                         | Request Body                             | Response Body                                  | Response Codes  |
|-----------|---------------------------------------------|------------------------------------------|------------------------------------------------|-----------------|
| GET		| `/api/users/{id}` 						  |											 | View user with specific ID					  | 200, 400		|
| PUT       | `/api/users/{id}`                           |                                          | Update user with specific ID                   | 200, 400        |
| DELETE    | `/api/users/{id}`                           |                                          | Remove user with specific ID                   | 200, 400        |
 
### Game
| HTTP Verb | URI                                         | Request Body                             | Response Body                                  | Response Codes  |
|-----------|---------------------------------------------|------------------------------------------|------------------------------------------------|-----------------|
| GET       | `/api/games/{gameId}`                		  |                                          | Find game by gameId                            | 200             |
| POST      | `/api/games`              	              |                                          | Create a new game  						      | 200, 400, 404   |
| PUT       | `/api/games/{gameId}`                       | 							             | Update existing game by gameId	              | 400, 404        |
| DELETE    | `/api/games/{gameId}`                       | 									     | Delete game by gameId			              | 204, 400, 404   |

### Gathering
| HTTP Verb | URI                                         | Request Body                             | Response Body                                  | Response Codes  |
|-----------|---------------------------------------------|------------------------------------------|------------------------------------------------|-----------------|
| GET       | `/api/gatherings/{gatheringId}`             |                                          | Show gathering by gatheringId                  | 200, 400, 404   |
| POST      | `/api/gatherings`                      	  |                                          | Create a new gatherings					      | 201, 400, 404   |
| PUT       | `/api/gatherings/{gatheringId}`             |                                          | Edit gathering by gatheringId    			  | 400, 404        |
| DELETE    | `/api/gatherings/{gatheringId}`             | 							             | Delete gathering ny gatheringId		          | 204, 400, 404   |


## **Technologies Used**
- **Frontend:**
  - Angular
- **Backend:**
  - Java
  - Spring Data JPA
  - Spring Boot
  - MySQL, MySQL Workbench
  - AWS
  - git, GitHub
  - Postman


  ## **Lessons Learned** 
- **User Authentication and Authorization**
  - Gained valuable experience in implementing user login and registration functionalities, including managing different privilege levels for CRUD operations
- **RESTful API Development**
  - Improved skills in designing and implementing RESTful APIs, including creating well-structured endpoints for managing users, reports, comments, and tags
- **Angular Frontend Development**
  - Developed a dynamic and responsive frontend using Angular, including features like data binding, form handling, and HTTP client service for API interaction
- **Version Control with Git**
  - Strengthened collaborative development skills through Git and GitHub for source control and team-based contributions
- **Git Branching**
  - Learned the importance of Git branching to ensure a smooth workflow by isolating new features from the main branch and minimizing conflicts during team collaboration
