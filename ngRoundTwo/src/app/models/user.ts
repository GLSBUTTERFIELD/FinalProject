export class User {

  id: number;
  username: string;
  password: string;
  enabled: boolean;
  firstName: string;
  lastName: string;
  role: string;
  email: string;
  imageUrl: string;
  biography: string;


  constructor(
    id: number = 0 ,
    username: string = '',
    password: string = '',
    enabled: boolean = false,
    firstName: string = '',
    lastName: string = '',
    role: string = '',
    email: string = '',
    imageUrl: string = '',
    biography: string = ''
  ){
    this.id = id;
    this.username = username;
    this.password = password;
    this.enabled = enabled;
    this.firstName = firstName;
    this.lastName = lastName;
    this.role  = role;
    this.email = email;
    this.imageUrl = imageUrl;
    this.biography = biography;
  }
}
