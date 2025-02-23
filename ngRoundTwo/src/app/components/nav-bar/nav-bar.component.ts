import { AuthService } from './../../services/auth.service';
import { CommonModule } from '@angular/common';
import { Component, AfterViewInit, OnInit } from '@angular/core';
declare var $: any;
import { FormsModule } from '@angular/forms';
import { User } from '../../models/user';
import { Router, RouterLink } from '@angular/router';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-nav-bar',
  imports: [
    CommonModule,
    FormsModule,
    RouterLink,
  ],
  templateUrl: './nav-bar.component.html',
  styleUrl: './nav-bar.component.css'
})

export class NavBarComponent implements OnInit{

  showSignUpForm: boolean = false;

  showLoginForm: boolean = false;

  newUser: User = new User();

  loggingInUser: User = new User();

  userButtons: boolean = false;

  loggedInUser: User | null = null;



  constructor(
    private authService: AuthService,
    private router: Router,
    private userService: UserService,
  ){}

  ngOnInit(): void {
    if(this.authService.checkLogin()){
     this.getLoggedInUser();
    }
  }


  register(addingUser: User){
    console.log(addingUser);
    if(addingUser){
      addingUser.enabled = true;
      this.authService.register(addingUser).subscribe({

        next: (registeredUser) => {
          this.authService.login(addingUser.username, addingUser.password).subscribe({
            next: (loggedInUser) => {
              // this.router.navigateByUrl('/todo');
              this.router.navigateByUrl('/home');
              this.toggleSignUp();
              this.getLoggedInUser();
            },
            error: (problem) => {
              console.error('NavBarComponent.register(): Error logging in user:');
              console.error(problem);
            }
          });
        },
        error: (fail) => {
          console.error('NavBarComponent.register(): Error registering account');
          console.error(fail);
        }

      });

    }
  }

  login(loginUser: User){
    this.authService.login(loginUser.username, loginUser.password).subscribe({
      next: (displayingUser) => {
        this.router.navigateByUrl('/home');
        this.toggleLogin();
        this.getLoggedInUser();
        console.log(displayingUser);
      }, error: (err) => {
        console.log(err);
      }
  })
}

toggleSignUp() {
  this.showSignUpForm = !this.showSignUpForm;
  this.showLoginForm = false;
}

toggleLogin() {
  this.showLoginForm = !this.showLoginForm;
  this.showSignUpForm = false;
}
toggleUserButtonView(): boolean {
  return !this.authService.checkLogin();
}

logout() {
  this.authService.logout();
  this.router.navigateByUrl("");
  this.loggedInUser = null;
  console.log("Logout button pressed");
}

getLoggedInUser() {
  this.authService.getLoggedInUser().subscribe({
      next: (loggedInUser) => {
        this.loggedInUser = loggedInUser;
      },
      error: (fail) => {
        console.log('NavBarComponent.loadAdmin(): failed to load admin', fail);
      }
    });
  }

}
